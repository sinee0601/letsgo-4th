package com.travel.placeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceJpaRepository extends JpaRepository<PlaceEntity, Long> {

    List<PlaceEntity> findByPlaceType(String placeType);

}
