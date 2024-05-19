package com.abhinav3254.authservice.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignUpDTO {
    private String username;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private String gender;
    private MultipartFile profilePicture;
}
