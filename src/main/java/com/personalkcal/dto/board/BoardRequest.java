package com.personalkcal.dto.board;

public class BoardRequest {

    public record WriteBoardRequest(
            Long memberId,
            String title,
            String content
            ){
    }
    public record UpdateBoardRequest(
            String title,
            String content
    ){

    }
}
