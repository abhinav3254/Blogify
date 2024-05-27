package com.abhinav3254.blogservice.service.impl;

import com.abhinav3254.blogservice.dto.MakeCommentDTO;
import com.abhinav3254.blogservice.dto.Response;
import com.abhinav3254.blogservice.exception.CustomException;
import com.abhinav3254.blogservice.jwt.MyUserDetailsService;
import com.abhinav3254.blogservice.model.Blog;
import com.abhinav3254.blogservice.model.Comments;
import com.abhinav3254.blogservice.repository.BlogRepository;
import com.abhinav3254.blogservice.repository.CommentsRepository;
import com.abhinav3254.blogservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Response makeComment(MakeCommentDTO makeCommentDTO) {
        Optional<Blog> blogOptional = blogRepository.findById(makeCommentDTO.getBlogId());
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            Comments comments = new Comments();
            comments.setComment(makeCommentDTO.getCommentText());
            comments.setCommentDate(new Date());
            comments.setLikes(0);
            comments.setEdited(false);
            comments.setUser(userDetailsService.getUserDetails());
            commentsRepository.save(comments);

            blog.getComments().add(comments);
            blogRepository.save(blog);

            return new Response("Comment Posted");
        } else throw new CustomException("Blog Not Found", HttpStatus.NOT_FOUND);

    }

    @Override
    public Response likeComment(Long commentId) {
        Optional<Comments> commentsOptional = commentsRepository.findById(commentId);
        if (commentsOptional.isEmpty()) throw new CustomException("Comment Not Found", HttpStatus.NOT_FOUND);
        else {
            Comments comments = commentsOptional.get();
            comments.setLikes(comments.getLikes()+1);
            commentsRepository.save(comments);
            return new Response("comment liked");
        }
    }
}
