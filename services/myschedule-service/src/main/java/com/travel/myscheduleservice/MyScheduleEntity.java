package com.travel.myscheduleservice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "my_schedules")
public class MyScheduleEntity {
    @Id //UUID 인코딩
    private String myScheduleId;
    private String title;
    @CreationTimestamp
    private LocalDateTime startAt;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private String budgetDetails;
    private String todoDetails;
    private boolean isShared;
    private String userEncodedId;
    @Builder.Default
    @OneToMany(mappedBy = "mySchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitItemEntity> visitItems = new ArrayList<>();
}
