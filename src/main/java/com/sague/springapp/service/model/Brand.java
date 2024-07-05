package com.sague.springapp.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Brand {
    private Integer id;
    private String name;
    private Integer warranty;
    private String country;
}

