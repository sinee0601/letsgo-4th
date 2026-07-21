# LetsGo ✈️ — 여행 일정 계획·공유 플랫폼 (4차 스프린트)

> 여행 일정을 만들고 동선·예산·할일을 관리하며, 동반자와 공유하거나 게시판에 공개할 수 있는 서비스.
> **3차 스프린트의 단일 Spring Boot(모놀리스) 구조를, 도메인별로 독립 배포되는 MSA(Microservice Architecture)로 전환**하고,
> **Spring Cloud Gateway + Netflix Eureka 기반 서비스 디스커버리/로드밸런싱**, **서비스별 독립 DB(Polyglot Persistence)**, **게이트웨이 집중형 JWT 인증**을 도입한 4차 스프린트 프로젝트입니다.

<p>
  <img src="https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/Spring%20Cloud-2025.1-6DB33F?logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/Spring%20Cloud%20Gateway-MVC-6DB33F?logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/Netflix%20Eureka-Discovery-DC382D?logo=netflix&logoColor=white">
  <img src="https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?logo=springsecurity&logoColor=white">
  <img src="https://img.shields.io/badge/JPA-Hibernate-59666C?logo=hibernate&logoColor=white">
  <img src="https://img.shields.io/badge/Gradle-Build-02303A?logo=gradle&logoColor=white">
  <img src="https://img.shields.io/badge/Docker-Container-2496ED?logo=docker&logoColor=white">
  <img src="https://img.shields.io/badge/Oracle%20Cloud-Infra-F80000?logo=oracle&logoColor=white">
</p>
<p>
  <img src="https://img.shields.io/badge/MariaDB-003545?logo=mariadb&logoColor=white">
  <img src="https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=white">
  <img src="https://img.shields.io/badge/MongoDB-47A248?logo=mongodb&logoColor=white">
  <img src="https://img.shields.io/badge/Redis-DC382D?logo=redis&logoColor=white">
  <img src="https://img.shields.io/badge/H2-Database-09476B?logo=h2&logoColor=white">
  <img src="https://img.shields.io/badge/Grafana-Loki-F46800?logo=grafana&logoColor=white">
</p>
<p>
  <img src="https://img.shields.io/badge/React-19-61DAFB?logo=react&logoColor=black">
  <img src="https://img.shields.io/badge/TypeScript-5-3178C6?logo=typescript&logoColor=white">
  <img src="https://img.shields.io/badge/Vite-8-646CFF?logo=vite&logoColor=white">
  <img src="https://img.shields.io/badge/Zustand-state-764ABC">
  <img src="https://img.shields.io/badge/Axios-HTTP-5A29E4?logo=axios&logoColor=white">
</p>

---

## 📌 프로젝트 개요

| 항목 | 내용 |
|------|------|
| **개발 형태** | 팀 프로젝트 · 애자일(스프린트) 기반 · GitHub Flow 협업 |
| **진행 방식** | **1차** Servlet/JSP → **2차** Spring Boot 리팩토링 + AI 챗봇 → **3차** FE/BE 분리 + React SPA + JWT/OAuth2 → **4차** MSA 전환 |
| **핵심 목표** | 모놀리스 → **마이크로서비스 분리** · **API Gateway 단일 진입점** · **Eureka 서비스 디스커버리 + 로드밸런싱** · **서비스별 독립 DB** · **게이트웨이 집중형 JWT 인증** |
| **1차 스프린트** | https://github.com/sinee0601/letsgo-1st |
| **2차 스프린트** | https://github.com/sinee0601/letsgo-2nd |
| **3차 스프린트** | https://github.com/sinee0601/letsgo-3rd-sprint-springboot |
| **프론트엔드(React)** | https://github.com/CloudKosta/letsgo-react-app |

---

## 🎯 4차 스프린트에서 달라진 점

> 3차의 **단일 Spring Boot 서버(모놀리스)** 구조를, 도메인별로 독립 배포·확장 가능한 **마이크로서비스 + API Gateway** 구조로 진화시켰습니다.

| 구분 | 3차 스프린트 | 4차 스프린트 |
|------|-------------|-------------|
| **아키텍처** | 단일 Spring Boot (모놀리스) | **MSA** (도메인별 독립 서비스) |
| **진입점** | 애플리케이션 서버 직접 호출 | **API Gateway 단일 진입점** (`:5531`) |
| **서비스 탐색** | 없음 (단일 프로세스 내 호출) | **Netflix Eureka** 서비스 디스커버리(`:8761`) |
| **부하 분산** | 없음 | **Spring Cloud LoadBalancer** (`lb://`, 클라이언트 사이드) |
| **데이터베이스** | 단일 MariaDB | **서비스별 독립 DB** (MariaDB/MySQL/PostgreSQL/Mongo/Redis/H2) |
| **인증 위치** | 애플리케이션 내 Security 필터 | **게이트웨이 집중형** JWT 필터 (계획) |
| **배포 단위** | 단일 WAR/JAR | 서비스별 **Docker 컨테이너** |
| **빌드 도구** | Maven | **Gradle** (서비스별 독립 빌드) |
| **런타임** | Java 17 · Spring Boot 3.5 | **Java 21 · Spring Boot 4.1 · Spring Cloud 2025.1** |

---

## 🏗️ 시스템 아키텍처

![LetsGo 4차 스프린트 아키텍처](<1차 프로젝트_아키텍처-LetsGO_4차_스프린트.drawio.png>)

전체 시스템은 **React 기반 프론트엔드(SPA)** 와, **API Gateway 뒤에서 도메인별로 분리된 다수의 Spring Boot 서비스**로 구성됩니다.
모든 백엔드 요청은 게이트웨이(`:5531`) 한 곳으로 들어가 **Eureka에 등록된 서비스로 로드밸런싱**되어 전달되고,
각 서비스는 **자신만의 DB**를 가지며 **Docker 컨테이너**로 Oracle Cloud 위에 배포됩니다.
자세한 구성은 [`ARCHITECTURE.md`](ARCHITECTURE.md) 참고.

### 🖥️ 프론트엔드 (React SPA)

| 구성 | 기술 | 역할 |
|------|------|------|
| **UI / 라우팅** | React 19 · React Router | 화면 구성 · 클라이언트 사이드 라우팅(SPA) |
| **상태 관리** | Zustand | 전역 상태(로그인/사용자/일정/장바구니 등) |
| **HTTP** | Axios | 게이트웨이(`:5531`) REST API 호출 · JWT 토큰 헤더 주입 |
| **빌드** | Vite (dev 포트 `5432`) | 개발 서버 · 번들링 |

> 프론트엔드 저장소: **https://github.com/CloudKosta/letsgo-react-app**
> 백엔드는 React 앱이 실제로 호출하는 계약(경로/body/반환)에 맞춰 개발합니다.

### 🔀 API Gateway + 서비스 디스커버리

```
[Client(React :5432)]
      │  (:5531)
      ▼
[API Gateway]  ── 경로 기반 라우팅 · lb:// 로드밸런싱 ──▶  각 도메인 서비스
      │                                                       ▲
      │  등록/조회                                              │ 등록
      ▼                                                       │
[Netflix Eureka :8761]  ◀──────────────────────────────────────
```

- **API Gateway** (`:5531`) — Spring Cloud Gateway(WebMVC). `RouterFunction` DSL로 경로별 라우팅, `lb("service-name")`으로 Eureka 인스턴스에 부하 분산
- **Eureka** (`:8761`) — 각 서비스가 기동 시 등록, 게이트웨이가 레지스트리를 조회해 대상 서비스를 탐색
- **인증(JWT)** — 목표 구성상 Spring Security 필터 체인(JWTFilter 포함)을 **게이트웨이에 집중**. 게이트웨이가 검증한 사용자 신원을 하위 서비스가 신뢰 (현재 인증 필터는 미구현, 아래 구현 현황 참고)

### 🌍 외부 연동

| API | 용도 | 연동 서비스 |
|-----|------|------------|
| **한국관광콘텐츠랩 (TourAPI)** | 관광지·숙박·음식점 공공 데이터 | `place` |
| **NaverMap API** | 지도 렌더링 · 장소 시각화 | Frontend |
| **도로명주소 API** | 주소 검색·정규화 | Frontend |
| **Gemini** | 여행 관련 AI 챗봇 질의응답 | `chat` |

---

## 🧩 서비스 구성 (Service per Domain · DB per Service)

> 도메인 단위로 **서비스를 분리**하고, 각 서비스는 **독립된 DB**를 소유합니다(서비스 간 직접 JOIN 없음 → 필요한 데이터는 복제/이벤트로 보강).

| 포트 | 서비스 | 전용 DB | 설명 |
|------|--------|---------|------|
| `:5531` | **api-gateway** | — | 단일 진입점 · 라우팅 · 로드밸런싱 · (JWT 인증 예정) |
| `:8761` | **discovery-eureka** | — | 서비스 레지스트리 |
| `:10001` | 👤 **user** | MariaDB | 회원 가입·로그인·인증 |
| `:10002` | 🗓️ **myschedule** | H2 | 나의 여행 일정 · 동선/예산/할일 · 방문 항목 |
| `:10003` | 🤝 **myShare** | MySQL | 일정 공유 · 동반자 권한 |
| `:10004` | 📢 **postSchedule** | MySQL | 일정 공유 게시판 · 좋아요 |
| `:10005` | 🚨 **postReport** | PostgreSQL | 게시글 신고 관리 |
| `:10006`, `:10007` | 📍 **place** (2 인스턴스, LB) | MariaDB | 장소 조회(관광/숙박/음식점) · TourAPI 연동 |
| `:10008` | 🤖 **chat** | MongoDB + Redis | AI 챗봇 · 대화 저장/캐시 · Gemini 연동 |

---

## 🛠️ 기술 스택

| 구분 | 기술 |
|------|------|
| **Language** | Java 21, TypeScript |
| **Frontend** | React 19, React Router, Zustand, Axios, Vite |
| **Backend** | Spring Boot 4.1, Spring MVC, Spring Security |
| **MSA** | Spring Cloud Gateway(WebMVC), Netflix Eureka, Spring Cloud LoadBalancer (Spring Cloud 2025.1) |
| **Auth** | JWT (게이트웨이 집중형, 예정) |
| **Persistence** | Spring Data JPA (Hibernate) |
| **Database** | MariaDB · MySQL · PostgreSQL · MongoDB · Redis · H2 (서비스별 상이) |
| **외부 API / AI** | TourAPI, NaverMap API, 도로명주소 API, Gemini(챗봇) |
| **Infra / 관측성** | Oracle Cloud, Docker, Grafana / Loki |
| **Build / Tool** | Gradle(서비스별), Vite/npm(프론트), Git/GitHub |

---

## ✨ 주요 설계 포인트

- **API Gateway 단일 진입점** — 프론트엔드는 게이트웨이(`:5531`) 한 곳만 호출하고, 게이트웨이가 경로를 보고 각 서비스로 분배. 서비스가 쪼개져도 클라이언트의 URL 계약은 그대로 유지
- **Eureka 서비스 디스커버리 + 클라이언트 사이드 로드밸런싱** — 서비스는 IP/포트 대신 **서비스 이름**으로 참조(`lb://user-service`). 인스턴스 증설(`place` 2대) 시 자동 분산
- **서비스별 독립 DB (Polyglot Persistence)** — 도메인 특성에 맞는 DB 선택(관계형 MariaDB/MySQL/PostgreSQL, 문서 Mongo, 캐시 Redis). 서비스 간 DB 직접 접근 없이 경계를 분리
- **게이트웨이 집중형 JWT 인증(계획)** — 인증/인가를 게이트웨이에 모아 각 서비스의 중복 로직 제거. 무상태(Stateless) 토큰 기반
- **React 계약 우선 개발** — 백엔드 엔드포인트는 React 앱이 실제 호출하는 경로·요청/응답 형태에 맞춰 구현
- **독립 배포(Docker)** — 서비스별 컨테이너로 개별 빌드·배포·확장

---

## 🚀 실행 방법

각 서비스는 독립 Gradle 프로젝트입니다. `lb://` 라우팅이 Eureka 등록에 의존하므로 **기동 순서**가 중요합니다.

```bash
git clone https://github.com/sinee0601/letsgo-4th.git
cd letsgo-4th/services
```

### 실행 순서

```bash
# 1) 서비스 레지스트리 (:8761)
cd discovery-eureka && ./gradlew bootRun      # Windows: gradlew.bat bootRun

# 2) 도메인 서비스 (각각 별도 터미널) — 기동되면서 Eureka에 등록됨
cd user-service       && ./gradlew bootRun     # :10001
cd myschedule-service && ./gradlew bootRun     # :10002
cd place-service      && ./gradlew bootRun     # :10006

# 3) API 게이트웨이 (:5531) — 마지막
cd api-gateway && ./gradlew bootRun
```

> ⚠️ 서비스가 Eureka에 등록되기까지 수 초 지연이 있습니다. 게이트웨이 기동 직후 첫 요청이 `503`이면 잠시 후 재시도하세요.

### 프론트엔드 (React · 포트 5432)

```bash
git clone https://github.com/CloudKosta/letsgo-react-app.git
cd letsgo-react-app
npm install
npm run dev     # http://127.0.0.1:5432  (API 대상: http://127.0.0.1:5531)
```

---

## 📂 프로젝트 구조

```
letsgo-4th
├─ ARCHITECTURE.md                          # 아키텍처 상세 요약
├─ 1차 프로젝트_아키텍처-...drawio.png         # 아키텍처 다이어그램
└─ services
   ├─ discovery-eureka     # 서비스 레지스트리 (:8761)
   ├─ api-gateway          # 게이트웨이 · 라우팅 · 로드밸런싱 (:5531)
   ├─ user-service         # 회원·인증 (:10001)
   ├─ myschedule-service   # 나의 일정·방문항목 (:10002)
   └─ place-service        # 장소 조회 (:10006)
```

각 서비스 내부는 표준 Spring Boot 레이아웃(`src/main/java`, `src/main/resources`)과 독립 Gradle 래퍼(`gradlew`, `build.gradle`, `settings.gradle`)를 가집니다.

---

## 📊 구현 현황 (2026-07-21 기준)

목표 아키텍처는 서비스 7종이며, 현재 리포지토리에 존재하는 것은 일부입니다.

| 서비스 | 코드 존재 | 비고 |
|--------|:--------:|------|
| discovery-eureka | ✅ | `:8761` |
| api-gateway | ✅ | user/myschedule/place 라우팅 완료 |
| user-service | ✅ | `:10001` |
| myschedule-service | ✅ | `:10002` |
| place-service | ✅ | `:10006` (LB 2번째 인스턴스 미구성) |
| myShare / postSchedule / postReport / chat | ❌ | 미생성 |

- **DB**: 현재 모든 서비스가 **H2 인메모리** 사용 → 목표는 서비스별 MariaDB/MySQL/PostgreSQL/Mongo/Redis
- **인증**: 게이트웨이 JWT 필터 **미구현** → `userEncodedId`를 임시로 요청 body로 전달 중
- **라우팅**: `postSchedule`/`chat` 등은 해당 서비스가 아직 없어 미연결

---

## 📝 커밋 컨벤션

`feat` · `fix` · `docs` · `style` · `refactor` 5가지 타입을 사용합니다. (3차 스프린트 컨벤션 계승)
