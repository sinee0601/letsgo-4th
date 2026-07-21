package com.travel.myscheduleservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyScheduleService {

    private final MyScheduleRepository myScheduleRepository;
    private final VisitItemRepository visitItemRepository;

    @Transactional
    public boolean addMySchedule(AddScheduleRequest request) {
        MyScheduleEntity mySchedule = MyScheduleEntity.builder()
                .myScheduleId(request.scheduleId())
                .title(request.title())
                .userEncodedId(request.userEncodedId())
                .isShared(false)
                .build();

        myScheduleRepository.save(mySchedule);
        return true;
    }

    @Transactional
    public boolean addVisitItem(String scheduleId, VisitItemRequest request) {
        MyScheduleEntity mySchedule = myScheduleRepository.getReferenceById(scheduleId);

        VisitItemEntity visitItem = VisitItemEntity.builder()
                .visitOrder(request.visitOrder())
                .placeId(Long.parseLong(request.placeId()))
                .mySchedule(mySchedule)
                .build();

        visitItemRepository.save(visitItem);
        return true;
    }
}
