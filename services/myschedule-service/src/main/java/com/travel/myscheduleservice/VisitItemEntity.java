package com.travel.myscheduleservice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visit_items")
public class VisitItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long visitItemId;
    private int visitOrder;
    private double distanceToNext;
    //플레이스정보(복제)
    private long placeId;
    private String title;
    private double mapx;
    private double mapy;
    private String placeType;
    @ManyToOne
    @JoinColumn(name = "myScheduleId")
    private MyScheduleEntity mySchedule;
}
