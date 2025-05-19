package org.example.week6.repository;

import org.example.week6.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);
}
