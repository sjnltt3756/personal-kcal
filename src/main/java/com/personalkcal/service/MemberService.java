package com.personalkcal.service;

import com.personalkcal.dto.member.LoginDTO;
import com.personalkcal.dto.member.MemberDTO;
import com.personalkcal.dto.member.RegisterDTO;
import com.personalkcal.dto.member.UpdateDTO;


public interface MemberService {
    MemberDTO loginMember(LoginDTO dto);

    RegisterDTO registerMember(RegisterDTO registerDto);

    MemberDTO viewMember(Long id);

    MemberDTO updateMember(Long id, UpdateDTO updateDTO);
}
