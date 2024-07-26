package com.personalkcal.dto.board;

import com.personalkcal.domain.Board;
import com.personalkcal.domain.Reply;

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
            String title,
            String nickname
    ) {
        public BoardListResponse(Board board) {
            this(
                    board.getTitle(),
                    board.getMember().getNickname(
                    ));
        }
    }

    public record ViewBoardResponse(
            String title,
            String content,
            String nickname,
            List<Reply> replies
    ) {
        public ViewBoardResponse(Board board) {
            this(
                    board.getTitle(),
                    board.getContent(),
                    board.getMember().getNickname(),
                    board.getReplies() != null ? board.getReplies() : new ArrayList<>()
            );
        }
    }

}
