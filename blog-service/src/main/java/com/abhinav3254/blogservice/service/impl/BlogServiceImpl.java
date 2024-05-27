package com.abhinav3254.blogservice.service.impl;

import com.abhinav3254.blogservice.dto.*;
import com.abhinav3254.blogservice.exception.CustomException;
import com.abhinav3254.blogservice.jwt.MyUserDetailsService;
import com.abhinav3254.blogservice.model.Blog;
import com.abhinav3254.blogservice.model.Comments;
import com.abhinav3254.blogservice.model.Likes;
import com.abhinav3254.blogservice.model.User;
import com.abhinav3254.blogservice.repository.BlogRepository;
import com.abhinav3254.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
        blog.setTitle(blogDTO.getTitle());
        blog.setTags(blogDTO.getTags());
        int readTime = blogDTO.getBlog().length()/1000;
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
    public List<GetBlogs> getAllBlogs() {
        List<GetBlogs> getBlogs = new ArrayList<>();

        List<Blog> blogs = blogRepository.findAll();

        for (Blog blog:blogs) {
            GetBlogs g = new GetBlogs();
            g.setId(blog.getId());
            g.setTitle(blog.getTitle());
            g.setContent(blog.getBlog());
            g.setCoverImage(encodeImage(blog.getCoverImage()));
            g.setPostedDate(blog.getCreateDate());
            g.setReadTime(blog.getReadTime());
            g.setTags(blog.getTags());

            User user = blog.getUser();

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setProfilePic(encodeImage(user.getProfilePicture()));

            g.setUser(userDTO);

            List<CommentDTO> commentDTOS = new ArrayList<>();
            for(Comments comments:blog.getComments()) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comments.getId());
                commentDTO.setComment(comments.getComment());
                commentDTO.setUsername(comments.getUser().getUsername());
                commentDTO.setLike(comments.getLikes());
                commentDTO.setCommentDate(comments.getCommentDate());

                commentDTOS.add(commentDTO);
            }

            int likeSum = 0;
            for (Likes likes:blog.getLikes()) {
                likeSum = likeSum + likes.getLikes();
            }

            g.setLikes(likeSum);

            g.setComments(commentDTOS);

            getBlogs.add(g);
        }

        return getBlogs;

    }

    private String encodeImage(byte[] imageBytes) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    @Override
    public Response deleteAll() {
        blogRepository.deleteAll();
        return new Response("All Blogs Deleted");
    }

}
