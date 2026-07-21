package com.travel.myscheduleservice;

public record AddScheduleRequest(
        String scheduleId,
        String title,
        String userEncodedId
) {
}
