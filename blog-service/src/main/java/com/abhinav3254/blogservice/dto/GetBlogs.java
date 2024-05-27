package com.abhinav3254.blogservice.dto;


import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class GetBlogs {
    private Long id;
    private String title;

    private String content;
    private String coverImage;
    private Date postedDate;
    private Integer readTime;

    private Integer likes;

    private UserDTO user;
    private List<CommentDTO> comments;
    private List<String> tags;

}
