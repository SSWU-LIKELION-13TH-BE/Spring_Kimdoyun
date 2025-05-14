package org.example.week6.controller;

import lombok.RequiredArgsConstructor;
import org.example.week6.dto.CommentDTO;
import org.example.week6.entity.Comment;
import org.example.week6.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/getComment")
    public Optional<Comment> getComment(@RequestParam(name="commentId") Long commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping("/postComment")
    public void postComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .author(commentDTO.getAuthor())
                .content(commentDTO.getContent())
                .build();
        commentService.postComment(comment);
    }

    @PutMapping("/putComment")
    public void putComment(@RequestBody CommentDTO commentDTO) {
        commentService.putComment(commentDTO);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }


}
