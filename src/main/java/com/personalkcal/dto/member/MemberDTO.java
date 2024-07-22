package com.personalkcal.dto.member;


import com.personalkcal.domain.Kcal;
import com.personalkcal.domain.Member;
import com.personalkcal.dto.kcal.KcalDTO;
import lombok.Data;


public record MemberDTO(
        Long id,
        String nickname,
        String gender,
        Double height,
        Double weight,
        Integer age,
        Kcal kcal
) {
    public MemberDTO(Member member){
        this(
                member.getId(),
                member.getNickname(),
                member.getGender(),
                member.getHeight(),
                member.getWeight(),
                member.getAge(),
                member.getKcal()
        );
    }

}
