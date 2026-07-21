package com.travel.placeservice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "places")
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long placeId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String addr1;
    @Column(nullable = false)
    private String addr2;
    private String lclssystm1;
    private String lclssystm2;
    private String lclssystm3;
    private String firstImage;
    private long likeCount;
    @Column(nullable = false)
    private double mapx;
    @Column(nullable = false)
    private double mapy;
    @Column(nullable = false)
    private String placeType;

}
