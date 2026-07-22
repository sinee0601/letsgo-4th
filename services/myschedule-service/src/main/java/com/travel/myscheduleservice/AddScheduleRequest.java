package com.travel.myscheduleservice;

public record AddScheduleRequest(
        String scheduleId,
        String title,
        String userEncodedId
) {
    public AddScheduleVO toVo() {
        return new AddScheduleVO(scheduleId, title, userEncodedId);
    }
}
