package com.personalkcal.service;

import com.personalkcal.dto.board.BoardRequest.*;
import com.personalkcal.dto.board.BoardResponse.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    WriteBoardResponse writeBoard(WriteBoardRequest request);

    Page<BoardListResponse> boardList(Pageable pageable);

    ViewBoardResponse viewBoard(Long boardId);

    UpdateBoardResponse updateBoard(Long boardId, UpdateBoardRequest request);

    void deleteBoard(Long id);
}
