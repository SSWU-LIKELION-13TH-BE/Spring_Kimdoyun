package org.example.week6.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.week6.dto.BoardDTO;
import org.example.week6.entity.Board;
import org.example.week6.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final S3Service s3Service;

    public Optional<Board> getBoard(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    @Transactional
    public void postBoard(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void putBoard(BoardDTO boardDTO) {
                Board board = Board.builder()
                        .boardId(boardDTO.getBoardId())
                        .title(boardDTO.getTitle())
                        .content(boardDTO.getContent())
                        .writer(boardDTO.getWriter())
                        .postDate(LocalDate.now())
                        .build();

                boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.deleteByBoardId(boardId);
    }



    @Transactional
    //이미지 포함 게시글 생성
    public void ImageBoard(BoardDTO request) throws IOException {

        String savedImageURI = s3Service.upload(request.getImage()); //이미지 s3에 업로드하고 url 가져오기

        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .image(savedImageURI) //img url 넣기
                .build();

        boardRepository.save(board);

    }

    @Transactional
    public String getImageUrl(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다."));

        String fileName = board.getImage();
        return s3Service.getImageUrl(fileName);
    }




}
