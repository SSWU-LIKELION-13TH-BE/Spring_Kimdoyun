package org.example.week6.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.week6.dto.CommentDTO;
import org.example.week6.entity.Comment;
import org.example.week6.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;


    public Optional<Comment> getComment(Long commentId) {
        return commentRepository.findByCommentId(commentId);
    }

    @Transactional
    public Comment postComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public void putComment(CommentDTO commentDTO) {
                Comment comment = Comment.builder()
                        .commentId(commentDTO.getCommentId())
                        .author(commentDTO.getAuthor())
                        .content(commentDTO.getContent())
                        .commentDate(LocalDate.now())
                        .build();
                commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteByCommentId(commentId);
    }
}
