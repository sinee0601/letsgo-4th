# LetsGO 4차 스프린트 — 아키텍처 요약

원본: `1차 프로젝트_아키텍처-LetsGO_4차_스프린트.drawio.png`

기존 모놀리스(3차, Spring Boot + Thymeleaf)를 **MSA(Microservice Architecture)** 로 전환하는 프로젝트다. 모바일 대상 React 앱이 새 프론트엔드이며, 모든 백엔드 요청은 API 게이트웨이 한 곳으로 들어가 각 도메인 서비스로 분배된다.

---

## 1. 전체 구성 (3계층)

```
[Frontend]  →  [API Gateway + Eureka + Security]  →  [도메인 서비스들 + 각자의 DB]
   React            Spring Cloud Gateway                7개 서비스 (Docker)
  (:5432)               (:5531 / :8761)                (:10001~:10008)
```

- **호스팅**: Oracle Cloud
- **컨테이너**: 모든 서비스 Docker화
- **런타임**: Spring Boot 4.1

---

## 2. Frontend

| 항목 | 내용 |
|------|------|
| 스택 | React + Vite + TypeScript (Node 컨테이너) |
| 서빙 포트 | `:5432` (브라우저 접속) |
| API 호출 대상 | `:5531` (게이트웨이, `VITE_API_BASE_URL`) |
| 외부 연동 | 네이버 지도, 도로명 주소 API |

브라우저는 화면(`:5432`)과 API(`:5531`)를 분리해서 접근한다.

---

## 3. API Gateway + 인증 (게이트웨이 계층)

Spring Cloud Gateway가 **단일 진입점**이며, 인증(Spring Security)이 게이트웨이에 위치한다.

- **포트**: `:5531` (프론트 → 게이트웨이)
- **로드밸런싱**: Eureka 기반 (`lb://`)
- **Security Filter Chain** (게이트웨이 내부):
  - Concurrent SessionFilter
  - CsrfFilter
  - **JWTFilter** ← JWT 인증의 핵심
  - UsernamePasswordAuthentication
  - Authentication Principal → Security Context Holder

> 인증이 게이트웨이에 집중되므로, 각 도메인 서비스는 게이트웨이가 검증·전달한 사용자 신원을 신뢰하는 구조를 지향한다.

### Service Discovery
- **Netflix Eureka** `:8761` — 각 서비스가 등록하고, 게이트웨이가 조회해 로드밸런싱

---

## 4. 도메인 서비스 (DB per Service)

각 서비스는 독립 컨테이너 + **독립 DB**를 가진다 (서비스별 DB 분리 원칙).

| 포트 | 서비스 | 전용 DB | 외부 연동 |
|------|--------|---------|-----------|
| `:10001` | `/user` | MariaDB | — |
| `:10002` | `/mySchedule` | H2 | — |
| `:10003` | `/myShare` | MySQL | — |
| `:10004` | `/postSchedule` | MySQL | — |
| `:10005` | `/postReport` | PostgreSQL | — |
| `:10006`, `:10007` | `/place` (2 인스턴스, LB) | MariaDB | 한국관광콘텐츠랩 API |
| `:10008` | `/chat` | MongoDB + Redis | Gemini (LLM) |

- `/place`는 인스턴스 2개(`:10006`, `:10007`)로 **수평 확장 + 로드밸런싱** 대상이다.
- `/chat`은 MongoDB(대화 저장)와 Redis(세션/캐시)를 함께 쓰고, 외부 Gemini API를 호출한다.

---

## 5. 외부 서비스 / API

| 대상 | 사용처 |
|------|--------|
| 네이버 지도 | Frontend |
| 도로명 주소 API | Frontend |
| 한국관광콘텐츠랩 | `/place` (관광 정보) |
| Gemini | `/chat` (AI 챗봇) |

---

## 6. 관측성 (Observability)

- **Grafana / Loki** — 로그 수집·모니터링

---

## 7. 구현 현황 vs 목표 (2026-07-21 기준)

목표 아키텍처는 서비스 7개지만, 현재 리포지토리에 존재하는 것은 일부다.

| 서비스 | 코드 존재 | 비고 |
|--------|:--------:|------|
| api-gateway | ✅ | 라우팅 설정 완료 (user/mySchedule/place) |
| discovery-eureka | ✅ | `:8761` |
| user-service | ✅ | `:10001` |
| myschedule-service | ✅ | `:10002` |
| place-service | ✅ | `:10006` (LB 2번째 인스턴스 미구성) |
| myShare-service | ❌ | 미생성 |
| postSchedule-service | ❌ | 미생성 |
| postReport-service | ❌ | 미생성 |
| chat-service | ❌ | 미생성 |

**차이 나는 점 (목표와 현재)**
- DB: 현재 모든 서비스가 **H2 인메모리** 사용 → 목표는 서비스별 MariaDB/MySQL/PostgreSQL/Mongo/Redis
- 인증: 게이트웨이 JWT 필터는 **미구현** → 현재 `userEncodedId`를 임시로 요청 body로 전달
- 게이트웨이 라우팅: `/postschedule`, `/chat` 등은 해당 서비스가 아직 없어 미연결
