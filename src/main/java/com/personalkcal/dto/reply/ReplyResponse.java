package com.personalkcal.dto.reply;

import com.personalkcal.domain.Reply;

public class ReplyResponse {
    public record WriteReplyResponse(
            String boardTitle,
            String boardContent,
            String reply,
            String nickname
    ){
      public WriteReplyResponse(Reply reply){
          this(
                  reply.getBoard().getTitle(),
                  reply.getBoard().getContent(),
                  reply.getReply(),
                  reply.getMember().getNickname()
          );
      }
    }

    public record UpdateReplyResponse(
            String boardTitle,
            String boardContent,
            String reply,
            String nickname
    ){
        public UpdateReplyResponse(Reply reply){
            this(
                    reply.getBoard().getTitle(),
                    reply.getBoard().getContent(),
                    reply.getMember().getNickname(),
                    reply.getReply()
                    );
        }
    }

    public record ReplyDto(String replyContent) {
        public ReplyDto(Reply reply) {
            this(reply.getReply());
        }
    }
}

