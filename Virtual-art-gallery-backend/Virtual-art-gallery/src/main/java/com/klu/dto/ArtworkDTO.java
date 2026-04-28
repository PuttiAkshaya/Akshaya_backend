package com.klu.dto;

import lombok.Data;

@Data
public class ArtworkDTO {

    private String title;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
    private Long artistId;
}