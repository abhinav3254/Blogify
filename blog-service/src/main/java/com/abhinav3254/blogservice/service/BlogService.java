package com.abhinav3254.blogservice.service;


import com.abhinav3254.blogservice.dto.BlogDTO;
import com.abhinav3254.blogservice.dto.Response;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {
    Response createBlog(BlogDTO blogDTO);
}
