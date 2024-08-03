/*
package com.personalkcal.service;

import com.personalkcal.Dto.LoginDTO;
import com.personalkcal.Dto.MemberDTO;
import com.personalkcal.Dto.RegisterDTO;
import com.personalkcal.domain.member.Member;
import com.personalkcal.mapper.MemberMapper;
import com.personalkcal.repository.member.MemberRepository;
import com.personalkcal.service.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    private MemberMapper memberMapper;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    private Member member;
    private MemberDTO memberDTO;
    private LoginDTO loginDTO;
    private RegisterDTO registerDTO;
    @BeforeEach
    void setUp() {
        member = new Member();
        member.setNickname("testNickname");

        memberDTO = new MemberDTO();
        memberDTO.setNickname("testNickname");
        memberDTO.setGender("남성");
        memberDTO.setHeight(177);
        memberDTO.setWeight(72);
        memberDTO.setAge(28);


        loginDTO = new LoginDTO();
        loginDTO.setNickname("testNickname");
    }

    @Test
    //@Disabled
    void testLoginMember() {
        when(memberRepository.findByNickname("testNickname")).thenReturn(Optional.of(member));
        when(memberMapper.toLoginDto(member)).thenReturn(loginDTO);

        LoginDTO result = memberService.loginMember(loginDTO);

        assertEquals("testNickname", result.getNickname());
        verify(memberRepository, times(1)).findByNickname("testNickname");
        verify(memberMapper, times(1)).toLoginDto(member);
    }

    @Test
    //@Disabled
    void testLoginMemberNotFound() {
        when(memberRepository.findByNickname("testNickname")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            memberService.loginMember(loginDTO);
        });

        assertEquals("회원을 찾을 수 없습니다.", exception.getMessage());
        verify(memberRepository, times(1)).findByNickname("testNickname");
    }

    @Test
    void testRegisterMember() {
        when(memberMapper.toMember(memberDTO)).thenReturn(member);
        when(memberRepository.save(any(Member.class))).thenReturn(member);
        when(memberMapper.toMemberDTO(member)).thenReturn(memberDTO);

        RegisterDTO result = memberService.registerMember(registerDTO);

        assertEquals("testNickname", result.getNickname());
        verify(memberMapper, times(1)).toMemberRegister(registerDTO);
        verify(memberRepository, times(1)).save(member);
        verify(memberMapper, times(1)).toRegisterDTO(member);


    }

    @Test
    //@Disabled
    void testViewMember() {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(memberMapper.toMemberDTO(member)).thenReturn(memberDTO);

        MemberDTO result = memberService.viewMember(1L);

        assertEquals("testNickname", result.getNickname());
        verify(memberRepository, times(1)).findById(1L);
        verify(memberMapper, times(1)).toMemberDTO(member);
    }

    @Test
    //@Disabled
    void testViewMemberNotFound() {
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            memberService.viewMember(1L);
        });

        assertEquals("해당 회원을 찾을 수 없습니다.", exception.getMessage());
        verify(memberRepository, times(1)).findById(1L);
    }
}*/
