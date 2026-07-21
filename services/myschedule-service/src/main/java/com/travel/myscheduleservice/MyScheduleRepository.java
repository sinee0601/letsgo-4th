package com.travel.myscheduleservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyScheduleRepository extends JpaRepository<MyScheduleEntity, String> {

}
