package com.personalkcal.dto.board;

import com.personalkcal.domain.board.Board;
import com.personalkcal.dto.reply.ReplyResponse.*;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {
    public record WriteBoardResponse(
            String title,
            String content,
            String nickname
    ){
        public WriteBoardResponse(Board board){
            this(
                    board.getTitle(),
                    board.getContent(),
                    board.getMember().getNickname()
            );
        }
    }
    public record UpdateBoardResponse(
            String title,
            String content,
            String nickname
    ){
        public UpdateBoardResponse(Board board){
            this(
                    board.getTitle(),
                    board.getContent(),
                    board.getMember().getNickname()
            );
        }
    }
    public record BoardListResponse(
            Long id,
            String title,
            String nickname,
            Integer viewCount
    ) {
        public BoardListResponse(Board board) {
            this(
                    board.getId(),
                    board.getTitle(),
                    board.getMember().getNickname(),
                    board.getViewCount()
            );
        }
    }

    public record ViewBoardResponse(
            Long id,
            String title,
            String content,
            String nickname,
            List<ReplyDto> replies
    ) {
        public ViewBoardResponse(Board board) {
            this(
                    board.getId(),
                    board.getTitle(),
                    board.getContent(),
                    board.getMember().getNickname(),
                    board.getReplies() != null ? board.getReplies().stream()
                            .map(ReplyDto::new)
                            .toList() : new ArrayList<>()
            );
        }
    }

}
