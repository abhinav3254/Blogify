package com.abhinav3254.blogservice.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BlogDTO {
    private String title;
    private String blog;
    private MultipartFile coverImage;
    private List<String> tags;
    private Boolean allowComments;
}
