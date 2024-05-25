package com.abhinav3254.blogservice.service.impl;

import com.abhinav3254.blogservice.dto.BlogDTO;
import com.abhinav3254.blogservice.dto.Response;
import com.abhinav3254.blogservice.exception.CustomException;
import com.abhinav3254.blogservice.jwt.MyUserDetailsService;
import com.abhinav3254.blogservice.model.Blog;
import com.abhinav3254.blogservice.repository.BlogRepository;
import com.abhinav3254.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Service
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
        blog.setTags(blogDTO.getTags());
        int readTime = blogDTO.getBlog().length()/200;
        if (readTime == 0) readTime = 1;
        blog.setReadTime(readTime);
        try {
            blog.setCoverImage(blogDTO.getCoverImage().getBytes());
        } catch (IOException e) {
            throw new CustomException("Error in Parsing Image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        blog.setUser(userDetailsService.getUserDetails());

        blogRepository.save(blog);
        return new Response("Posted");
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}
