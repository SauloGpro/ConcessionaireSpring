package com.sague.springapp.controller.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
}


