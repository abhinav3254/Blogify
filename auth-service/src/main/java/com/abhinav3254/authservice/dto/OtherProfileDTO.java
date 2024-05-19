package com.abhinav3254.authservice.dto;

import lombok.Data;

import java.util.Date;


@Data
public class OtherProfileDTO {
    private Long id;
    private String username;
    private String name;
    private String gender;
    private String profilePicture;
    private Date registerDate;
}
