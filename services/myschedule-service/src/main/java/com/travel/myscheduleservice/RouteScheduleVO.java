package com.travel.myscheduleservice;

public record RouteScheduleVO(
        String visitId,
        String visitOrder,
        String placeId,
        String title,
        double distanceToNext,
        String scheduleType) {

    public static RouteScheduleVO from(VisitItemEntity entity) {
        return new RouteScheduleVO(
                String.valueOf(entity.getVisitItemId()),
                String.valueOf(entity.getVisitOrder()),
                String.valueOf(entity.getPlaceId()),
                entity.getTitle(),
                entity.getDistanceToNext(),
                entity.getPlaceType());
    }
}
