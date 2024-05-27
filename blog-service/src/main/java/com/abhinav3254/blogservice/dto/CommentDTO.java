package com.abhinav3254.blogservice.dto;

import lombok.Data;

import java.util.Date;


@Data
public class CommentDTO {
    private Long id;
    private String comment;
    private Date commentDate;
    private String username;
    private Integer like;
}
