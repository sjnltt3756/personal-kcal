package com.personalkcal.dto.member;

public class MemberRequest {

    public record RegisterRequest(
            String nickname,
            String gender,
            Double height,
            Double weight,
            Integer age
    ){

    }
    public record LoginRequest(
            String nickname
    ){

    }


    public record UpdateRequest(
            String nickname,
            Double height,
            Double weight,
            Integer age
    ){

    }
}
