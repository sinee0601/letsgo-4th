package com.travel.myscheduleservice;

public record ScheduleDetailVO(
        String scheduleTitle,
        String startAt,
        String budgetDetail,
        String todoDetail) {

    public static ScheduleDetailVO from(MyScheduleEntity entity) {
        return new ScheduleDetailVO(
                entity.getTitle(),
                entity.getStartAt() != null ? entity.getStartAt().toLocalDate().toString() : null,
                entity.getBudgetDetails(),
                entity.getTodoDetails());
    }
}
