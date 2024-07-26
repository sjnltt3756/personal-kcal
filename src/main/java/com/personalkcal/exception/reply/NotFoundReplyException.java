package com.personalkcal.exception.reply;

public class NotFoundReplyException extends RuntimeException{
    public void NotFoundReplyException() {
    }

    public NotFoundReplyException(String message) {
        super(message);
    }

    public NotFoundReplyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundReplyException(Throwable cause) {
        super(cause);
    }
}
