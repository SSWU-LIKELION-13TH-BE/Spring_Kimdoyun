package org.example.week6.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.week6.dto.BoardDTO;
import org.example.week6.entity.Board;
import org.example.week6.service.S3Service;

import org.example.week6.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/getBoard")
    public Optional<Board> getBoard(@RequestParam(name = "boardId") Long boardId) {
        return boardService.getBoard(boardId);
    }

    @PostMapping("/postBoard")
    public void postBoard(@RequestBody BoardDTO boardDTO) {
        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();
        boardService.postBoard(board);
    }

    @PutMapping("/putBoard")
    public void putBoard(@RequestBody BoardDTO boardDTO) {
        boardService.putBoard(boardDTO);
    }

    @DeleteMapping("/deleteBoard{boardId}")
    public void deleteBoard(@RequestParam(name = "boardId") Long boardId) {
        boardService.deleteBoard(boardId);
    }


    // 이미지 포함 게시글 올리기
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute BoardDTO imageBoardDTO) {
        try {
            BoardDTO request = BoardDTO.builder()
                    .title(imageBoardDTO.getTitle())
                    .content(imageBoardDTO.getContent())
                    .image(imageBoardDTO.getImage())
                    .writer(imageBoardDTO.getWriter())
                    .build();

            boardService.ImageBoard(request);

            return ResponseEntity.ok("파일 업로드 성공");
        } catch (Exception e) {
            log.error("파일 업로드 실패", e);
            return ResponseEntity.status(400).build();
        }
    }
}
