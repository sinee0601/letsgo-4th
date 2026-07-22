package com.travel.myscheduleservice;

import java.util.List;

/**
 * Service -> Controller 전송용 VO
 * 일정 상세(헤더 정보 + 방문 경로 목록)를 하나로 묶는다.
 */
public record ScheduleDetailResponse(
        ScheduleDetailVO schedule,
        List<RouteScheduleVO> route
) {
}
