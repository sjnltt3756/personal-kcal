package com.personalkcal.dto.member;

import com.personalkcal.domain.Member;
import lombok.Data;
import org.hibernate.sql.Update;


public record UpdateDTO(
        String nickname,
        Double height,
        Double weight,
        Integer age
) {
    public UpdateDTO(Member member){
        this(
                member.getNickname(),
                member.getHeight(),
                member.getWeight(),
                member.getAge()
        );
    }



}
