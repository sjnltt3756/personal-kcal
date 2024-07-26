package com.personalkcal.service;

import com.personalkcal.dto.member.MemberRequest.*;
import com.personalkcal.dto.member.MemberResponse.*;


public interface MemberService {
    LoginResponse loginMember(LoginRequest request);

    RegisterResponse registerMember(RegisterRequest request);

    ViewResponse viewMember(Long id);

    UpdateResponse updateMember(Long id, UpdateRequest request);

    void deleteMember(Long id);
}
