package com.personalkcal.dto.member;

import com.personalkcal.domain.Member;
import lombok.Data;


public record RegisterDTO(
        String nickname,
        String gender,
        Double height,
        Double weight,
        Integer age
) {
    public RegisterDTO(Member member){
        this(
          member.getNickname(),
          member.getGender(),
          member.getHeight(),
          member.getWeight(),
          member.getAge()
        );
    }

}
