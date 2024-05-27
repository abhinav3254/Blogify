package com.abhinav3254.blogservice.dto;


import lombok.Data;

@Data
public class MakeCommentDTO {
    private Long blogId;
    private String commentText;
}
