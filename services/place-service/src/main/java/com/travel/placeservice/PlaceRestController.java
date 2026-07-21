package com.travel.placeservice;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceRestController {

    private final PlaceService placeService;

    @GetMapping("/leisure")
    public List<PlaceVO> getLeisureList(){
        return placeService.searchPlaces("LEISURE");
    }

    @GetMapping("/restaurant")
    public List<PlaceVO> getRestaurantList(){
        return placeService.searchPlaces("RESTAURANT");
    }

    @GetMapping("/stay")
    public List<PlaceVO> getStayList(){
        return placeService.searchPlaces("STAY");
    }
}
