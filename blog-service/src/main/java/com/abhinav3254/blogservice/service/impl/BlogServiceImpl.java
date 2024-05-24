package com.abhinav3254.blogservice.service.impl;

import com.abhinav3254.blogservice.dto.BlogDTO;
import com.abhinav3254.blogservice.dto.Response;
import com.abhinav3254.blogservice.jwt.MyUserDetailsService;
import com.abhinav3254.blogservice.model.Blog;
import com.abhinav3254.blogservice.repository.BlogRepository;
import com.abhinav3254.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    public Response createBlog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setAllowComments(blog.isAllowComments());
        blog.setCreateDate(new Date());
        blog.setBlog(blogDTO.getBlog());
        int readTime = blogDTO.getBlog().length()/200;
        if (readTime == 0) readTime = 1;
        blog.setReadTime(readTime);
        blog.setUser(userDetailsService.getUserDetails());
        return new Response("Posted");
    }
}
