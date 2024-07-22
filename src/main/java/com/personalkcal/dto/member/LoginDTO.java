package com.personalkcal.dto.member;

import com.personalkcal.domain.Member;
import lombok.Getter;
import lombok.Setter;


public record LoginDTO (
        String nickname
){
    public LoginDTO(Member member){
        this(member.getNickname());
    }

}
