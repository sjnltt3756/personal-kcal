package com.personalkcal.controller.board;

import com.personalkcal.dto.board.BoardRequest.*;
import com.personalkcal.dto.board.BoardResponse.*;
import com.personalkcal.service.board.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<?> createBoard(@Valid @RequestBody WriteBoardRequest request) {
        WriteBoardResponse response = boardService.writeBoard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 게시글 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> boardList(@Valid Pageable pageable) {
        Page<BoardListResponse> response = boardService.boardList(pageable);
        return ResponseEntity.ok(response);
    }

    // 특정 게시글 조회
    @GetMapping("/view/{boardId}")
    public ResponseEntity<?> viewBoard(@Valid @PathVariable Long boardId) {
        ViewBoardResponse response = boardService.viewBoard(boardId);
        return ResponseEntity.ok(response);
    }

    // 게시글 수정
    @PutMapping("/update/{boardId}")
    public ResponseEntity<?> updateBoard(@Valid @PathVariable Long boardId, @RequestBody UpdateBoardRequest request) {
        UpdateBoardResponse response = boardService.updateBoard(boardId, request);
        return ResponseEntity.ok(response);
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBoard(@Valid @PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok("게시글 삭제 완료");
    }
}
