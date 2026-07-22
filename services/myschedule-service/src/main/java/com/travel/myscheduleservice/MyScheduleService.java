package com.travel.myscheduleservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyScheduleService {

    private final MyScheduleRepository myScheduleRepository;
    private final VisitItemRepository visitItemRepository;

    @Transactional(readOnly = true)
    public ScheduleDetailVO getScheduleDetail(String scheduleId) {
        MyScheduleEntity mySchedule = myScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다: " + scheduleId));

        return ScheduleDetailVO.from(mySchedule);
    }

    @Transactional(readOnly = true)
    public List<RouteScheduleVO> getScheduleRoute(String scheduleId) {
        MyScheduleEntity mySchedule = myScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다: " + scheduleId));

        List<RouteScheduleVO> route = new ArrayList<>();
        for (VisitItemEntity visitItem : mySchedule.getVisitItems()) {
            route.add(RouteScheduleVO.from(visitItem));
        }
        return route;
    }

    @Transactional
    public boolean addMySchedule(AddScheduleVO vo) {
        MyScheduleEntity mySchedule = MyScheduleEntity.builder()
                .myScheduleId(vo.scheduleId())
                .title(vo.title())
                .userEncodedId(vo.userEncodedId())
                .isShared(false)
                .build();

        myScheduleRepository.save(mySchedule);
        return true;
    }

    @Transactional
    public boolean addVisitItem(VisitItemVO vo) {
        MyScheduleEntity mySchedule = myScheduleRepository.getReferenceById(vo.scheduleId());

        VisitItemEntity visitItem = VisitItemEntity.builder()
                .visitOrder(vo.visitOrder())
                .placeId(vo.placeId())
                .mySchedule(mySchedule)
                .build();

        visitItemRepository.save(visitItem);
        return true;
    }
}
