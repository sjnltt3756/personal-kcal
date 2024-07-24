package com.personalkcal.exception.member;

public class NotFoundMemberException extends RuntimeException{

    public void NotFoundMemberException() {
    }

    public NotFoundMemberException(String message) {
        super(message);
    }

    public NotFoundMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundMemberException(Throwable cause) {
        super(cause);
    }
}
