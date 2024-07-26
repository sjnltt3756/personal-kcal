package com.personalkcal.exception.board;

public class NotFoundBoardException extends RuntimeException{
    public void NotFoundMemberException() {
    }

    public NotFoundBoardException(String message) {
        super(message);
    }

    public NotFoundBoardException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundBoardException(Throwable cause) {
        super(cause);
    }
}
