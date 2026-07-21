package com.travel.myscheduleservice;

public record VisitItemRequest(
        int visitOrder,
        String placeId
) {
}
