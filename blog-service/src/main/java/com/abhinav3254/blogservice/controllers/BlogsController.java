package com.abhinav3254.blogservice.controllers;


import com.abhinav3254.blogservice.dto.BlogDTO;
import com.abhinav3254.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog-service/blogs")
public class BlogsController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<?> createBlog(@ModelAttribute BlogDTO blogDTO) {
        return ResponseEntity.ok(blogService.createBlog(blogDTO));
    }

}
