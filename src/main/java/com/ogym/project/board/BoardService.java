package com.ogym.project.board;

import com.ogym.project.DataNotFoundException;
import com.ogym.project.boardCategory.BoardCategory;
import com.ogym.project.boardCategory.BoardCategoryRepository;
import com.ogym.project.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    public Board create(String title, String content, SiteUser author, BoardCategory boardCategory) {

        Board board = new Board();

        board.setTitle(title);
        board.setContent(content);
        board.setAuthor(author);
        board.setBoardCategory(boardCategory);
        board.setCreateDate(LocalDateTime.now());
        board.setHit(0);

        this.boardRepository.save(board);

        return board;
    }

    public Page<Board> getList(int boardPage, String searchKeyWord) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(boardPage, 10, Sort.by(sorts));
        return this.boardRepository.findAll(pageable);
    }

    public Board getBoard(Long id) {
        Optional<Board> ob = this.boardRepository.findById(id);
        if (ob.isPresent()) {
            return ob.get();
        } else {
            throw new DataNotFoundException("board not found");
        }
    }
}
