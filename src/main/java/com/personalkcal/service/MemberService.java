package com.personalkcal.service;

import com.personalkcal.Dto.LoginDTO;
import com.personalkcal.Dto.MemberDTO;
import com.personalkcal.Dto.RegisterDTO;
import com.personalkcal.Dto.UpdateDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface MemberService {
    MemberDTO loginMember(LoginDTO dto);

    RegisterDTO registerMember(RegisterDTO registerDto);

    MemberDTO viewMember(Long id);

    MemberDTO updateMember(Long id, UpdateDTO updateDTO);
}
