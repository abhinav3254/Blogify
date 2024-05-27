package com.abhinav3254.blogservice.controllers;


import com.abhinav3254.blogservice.dto.MakeCommentDTO;
import com.abhinav3254.blogservice.dto.Response;
import com.abhinav3254.blogservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/blog-service/comments")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/make-comment")
    public ResponseEntity<?> makeComment(@RequestBody MakeCommentDTO makeCommentDTO) {
        return ResponseEntity.ok(commentService.makeComment(makeCommentDTO));
    }

    @PutMapping("/like-comment/{commentId}")
    public ResponseEntity<?> likeComment(@PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.likeComment(commentId));
    }

}
