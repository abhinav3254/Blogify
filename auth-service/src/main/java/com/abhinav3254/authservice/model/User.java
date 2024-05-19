package com.abhinav3254.authservice.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private String gender;

    private byte[] profilePicture;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}
