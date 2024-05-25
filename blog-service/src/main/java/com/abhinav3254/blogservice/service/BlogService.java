package com.abhinav3254.blogservice.service;


import com.abhinav3254.blogservice.dto.BlogDTO;
import com.abhinav3254.blogservice.dto.GetBlogs;
import com.abhinav3254.blogservice.dto.Response;
import com.abhinav3254.blogservice.model.Blog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    Response createBlog(BlogDTO blogDTO);

    List<GetBlogs> getAllBlogs();

    Response deleteAll();
}
