package com.personalkcal.mapper;

import com.personalkcal.Dto.*;
import com.personalkcal.domain.Kcal;
import com.personalkcal.domain.Member;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    // KcalDto와 Kcal 간의 변환 매핑
    Kcal toKcal(KcalDTO kcalDto);
    KcalDTO toKcalDto(Kcal kcal);


    @Mapping(source = "kcalDto", target = "kcal")
    Member toMember(MemberDTO memberDto);

    @Mapping(source = "kcal", target = "kcalDto")
    MemberDTO toMemberDTO(Member member);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "kcal", ignore = true)
    Member toMemberRegister(RegisterDTO registerDto);

    RegisterDTO toRegisterDTO(Member member);

    @Mapping(source = "nickname", target = "nickname")
    LoginDTO toLoginDto(Member member);

    @Mapping(target = "id", ignore = true) // ID는 업데이트하지 않음
    @Mapping(target = "gender", ignore = true) // 성별도 업데이트하지 않음
    @Mapping(target = "kcal", ignore = true)
    @Mapping(target = "height", expression = "java(Double.parseDouble(updateDTO.getHeight()))")
    @Mapping(target = "weight", expression = "java(Double.parseDouble(updateDTO.getWeight()))")
    void updateDTOToMember(UpdateDTO updateDTO, @MappingTarget Member member);

}
