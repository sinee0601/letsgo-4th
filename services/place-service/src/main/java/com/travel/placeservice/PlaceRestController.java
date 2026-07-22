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

    @GetMapping("/leisureList")
    public List<PlaceVO> getLeisureList(){
        return placeService.searchPlaces("LEISURE");
    }

    @GetMapping("/restaurantList")
    public List<PlaceVO> getRestaurantList(){
        return placeService.searchPlaces("RESTAURANT");
    }

    @GetMapping("/stayList")
    public List<PlaceVO> getStayList(){
        return placeService.searchPlaces("STAY");
    }
}
