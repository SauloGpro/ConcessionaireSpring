package com.sague.springapp.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarResponse {
    private Integer id;
    private String brand;
    private String model;
    private Integer mileage;
    private Double price;
    private Integer year;
    private String description;
    private String colour;
    private String fuelType;
    private Integer numDoors;
}


