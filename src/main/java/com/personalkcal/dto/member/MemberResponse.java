package com.personalkcal.dto.member;

import com.personalkcal.domain.Kcal;
import com.personalkcal.domain.Member;

public class MemberResponse {
    public record RegisterResponse(
            String nickname,
            String gender,
            Double height,
            Double weight,
            Integer age)
    {
        public RegisterResponse(Member member) {
            this(
                    member.getNickname(),
                    member.getGender(),
                    member.getHeight(),
                    member.getWeight(),
                    member.getAge()
            );
        }
    }

    public record LoginResponse(Long id, String nickname){

        public LoginResponse(Member member){
            this(
                    member.getId(),
                    member.getNickname()
            );
        }
    }

    public record UpdateResponse(
            String nickname,
            String gender,
            Double height,
            Double weight,
            Integer age
    ){
        public UpdateResponse(Member member) {
            this(
                    member.getNickname(),
                    member.getGender(),
                    member.getHeight(),
                    member.getWeight(),
                    member.getAge()
            );
        }
    }

    public record ViewResponse(
            String nickname,
            String gender,
            Double height,
            Double weight,
            Integer age,
            Kcal kcal
    ){
        public ViewResponse(Member member){
            this(
                    member.getNickname(),
                    member.getGender(),
                    member.getHeight(),
                    member.getWeight(),
                    member.getAge(),
                    member.getKcal()
            );
        }

    }
}
