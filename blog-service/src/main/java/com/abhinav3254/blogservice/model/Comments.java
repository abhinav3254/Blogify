package com.abhinav3254.blogservice.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;


    // this is likes on comment
    private int likes;

    private boolean edited;

    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;

}
