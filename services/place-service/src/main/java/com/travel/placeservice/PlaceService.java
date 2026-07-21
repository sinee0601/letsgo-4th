package com.travel.placeservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceJpaRepository placeJpaRepository;
    private final ModelMapper modelMapper;

    public List<PlaceVO> searchPlaces(String placeType) {
        List<PlaceEntity> byPlaceType = placeJpaRepository.findByPlaceType(placeType);

        return byPlaceType.stream()
                .map(entity -> modelMapper.map(entity, PlaceVO.class))
                .collect(Collectors.toList());
    }

}
