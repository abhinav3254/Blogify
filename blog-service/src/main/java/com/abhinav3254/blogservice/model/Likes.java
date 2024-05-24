package com.abhinav3254.blogservice.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer likes;

    @OneToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date likeDate;

}
