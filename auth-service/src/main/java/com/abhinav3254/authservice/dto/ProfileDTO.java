package com.abhinav3254.authservice.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class ProfileDTO {

    private Long id;
    private String username;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String gender;

    private String profilePicture;

    private Date registerDate;
}
