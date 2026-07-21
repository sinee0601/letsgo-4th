package com.travel.placeservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceVO {
    private Long placeId;
    private String title;
    private String firstImage;
    private String addr1;
    private String addr2;
    private double mapx;
    private double mapy;
    private Long likeCount;
    private String placeType;
    private String lclssystm1;
    private String lclssystm2;
    private String lclssystm3;
}
