package com.travel.myscheduleservice;

public record VisitItemRequest(
        int visitOrder,
        String placeId
) {
    public VisitItemVO toVo(String scheduleId) {
        return new VisitItemVO(scheduleId, visitOrder, Long.parseLong(placeId));
    }
}
