package com.personalkcal.mapper;

import com.personalkcal.Dto.LoginDTO;
import com.personalkcal.Dto.MemberDTO;
import com.personalkcal.domain.Member;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);


    @Mapping(target = "id", ignore = true) // ID는 자동 생성되므로 매핑에서 제외
    Member toMember(MemberDTO memberDto);

    MemberDTO toMemberDTO(Member member);

    @Mapping(source = "nickname", target = "nickname")
    LoginDTO toLoginDto(Member member);



}
