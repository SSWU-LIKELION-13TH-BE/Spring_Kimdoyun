package org.example.week6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id", unique = true, nullable = false)
    private Long commentId;

    @Column(length = 15, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String content;

    private LocalDate commentDate;

    @PrePersist
    protected void onCreate() {
        this.commentDate = LocalDate.now();
    }

}
