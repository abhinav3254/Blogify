package com.abhinav3254.authservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class AuthResponse <T> {

    public AuthResponse() {
    }

    public AuthResponse(T response, Date date) {
        this.response = response;
        this.date = date;
    }

    public AuthResponse(T response) {
        this.response = response;
        this.date = new Date();
    }

    private T response;
    private Date date;
}
