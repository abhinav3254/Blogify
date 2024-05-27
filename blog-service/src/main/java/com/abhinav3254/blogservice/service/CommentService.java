package com.abhinav3254.blogservice.service;


import com.abhinav3254.blogservice.dto.MakeCommentDTO;
import com.abhinav3254.blogservice.dto.Response;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Response makeComment(MakeCommentDTO makeCommentDTO);

    Response likeComment(Long commentId);
}
