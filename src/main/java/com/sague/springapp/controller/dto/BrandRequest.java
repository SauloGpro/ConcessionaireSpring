package com.sague.springapp.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandRequest {
    private String name;
    private Integer warranty;
    private String country;
}
