package com.personalkcal.controller;

import com.personalkcal.dto.board.BoardRequest.*;
import com.personalkcal.dto.board.BoardResponse.*;
import com.personalkcal.service.BoardService;
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
    public ResponseEntity<?> createBoard(@RequestBody WriteBoardRequest request) {
        WriteBoardResponse response = boardService.writeBoard(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 게시글 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> boardList(Pageable pageable) {
        Page<BoardListResponse> response = boardService.boardList(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 특정 게시글 조회
    @GetMapping("/view/{boardId}")
    public ResponseEntity<?> viewBoard(@PathVariable Long boardId) {
        ViewBoardResponse response = boardService.viewBoard(boardId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 게시글 수정
    @PutMapping("/update/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequest request) {
        UpdateBoardResponse response = boardService.updateBoard(boardId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
