package com.sague.springapp.controller.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}

