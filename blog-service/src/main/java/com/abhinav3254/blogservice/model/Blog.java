package com.abhinav3254.blogservice.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String blog;

    @ManyToOne
    private User user;

    private Integer readTime;

    @ElementCollection
    private List<String> tags;

    private boolean allowComments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Likes> likes;

    private byte[] coverImage;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
}
