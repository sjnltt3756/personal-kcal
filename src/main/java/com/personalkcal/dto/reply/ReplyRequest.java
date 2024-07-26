package com.personalkcal.dto.reply;



public class ReplyRequest {
    public record WriteReplyRequest(
            Long memberId,
            Long boardId,
            String reply
    ){
    }

    public record UpdateReplyRequest(
            String reply
    ){}
}
