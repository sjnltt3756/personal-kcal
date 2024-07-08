package com.personalkcal.mapper;

import com.personalkcal.Dto.LoginDTO;
import com.personalkcal.Dto.MemberDTO;
import com.personalkcal.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(target = "id", ignore = true) // ID는 자동 생성되므로 매핑에서 제외
    MemberDTO toMemberDTO(Member member);

    LoginDTO toLoginDto(Member member);


   /*
   @Mapping(target = "id", ignore = true) // ID는 자동 생성되므로 매핑에서 제외
    Member toMember(MemberDTO memberDTO);
    @Mapping(source = "nickname",target = "nickname")
    Member toLogin(LoginDTO LoginDTO);
    */
}
