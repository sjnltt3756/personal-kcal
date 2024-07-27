package com.personalkcal.controller.exception;

import com.personalkcal.exception.board.NotFoundBoardException;
import com.personalkcal.exception.member.ExistMemberException;
import com.personalkcal.exception.member.NotFoundMemberException;
import com.personalkcal.exception.reply.NotFoundReplyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundMemberException(NotFoundMemberException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundBoardException(NotFoundBoardException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundReplyException(NotFoundReplyException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleExistMemberException(ExistMemberException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<String> handleInterruptedException(InterruptedException e) {
        Thread.currentThread().interrupt();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Thread interrupted");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
    }
}
