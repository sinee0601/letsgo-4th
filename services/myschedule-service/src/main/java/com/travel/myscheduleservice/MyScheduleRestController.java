package com.travel.myscheduleservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myschedule/api")
@RequiredArgsConstructor
public class MyScheduleRestController {

    private final MyScheduleService myScheduleService;

    @PostMapping
    public boolean addMySchedule(@RequestBody AddScheduleRequest request) {
        return myScheduleService.addMySchedule(request);
    }

    @PostMapping("/{scheduleId}/visit")
    public boolean addVisitItem(@PathVariable String scheduleId,
                                @RequestBody VisitItemRequest request) {
        return myScheduleService.addVisitItem(scheduleId, request);
    }
}
