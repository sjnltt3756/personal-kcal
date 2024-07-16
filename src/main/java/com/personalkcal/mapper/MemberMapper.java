package com.personalkcal.mapper;

import com.personalkcal.Dto.KcalDto;
import com.personalkcal.Dto.LoginDTO;
import com.personalkcal.Dto.MemberDTO;
import com.personalkcal.Dto.RegisterDTO;
import com.personalkcal.domain.Kcal;
import com.personalkcal.domain.Member;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    // KcalDto와 Kcal 간의 변환 매핑
    Kcal toKcal(KcalDto kcalDto);
    KcalDto toKcalDto(Kcal kcal);

    @Mapping(target = "id", ignore = true) // ID는 자동 생성되므로 매핑에서 제외
    @Mapping(source = "kcalDto", target = "kcal")
    Member toMember(MemberDTO memberDto);

    @Mapping(source = "kcal", target = "kcalDto")
    MemberDTO toMemberDTO(Member member);


    @Mapping(target = "id", ignore = true) // ID는 자동 생성되므로 매핑에서 제외
    @Mapping(target = "kcal", ignore = true)
    Member toMemberRegister(RegisterDTO registerDto);

    RegisterDTO toRegisterDTO(Member member);

    @Mapping(source = "nickname", target = "nickname")
    LoginDTO toLoginDto(Member member);



}
