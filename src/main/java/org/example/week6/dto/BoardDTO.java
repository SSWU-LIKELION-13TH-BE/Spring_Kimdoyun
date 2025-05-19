package org.example.week6.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BoardDTO {
    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private Date postDate;
    private MultipartFile image;

}
