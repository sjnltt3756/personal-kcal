package com.personalkcal.service;

import com.personalkcal.Dto.LoginDTO;
import com.personalkcal.Dto.MemberDTO;
import com.personalkcal.mapper.MemberMapper;
import com.personalkcal.domain.Member;
import com.personalkcal.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;


    /**
     * 닉네임으로 로그인
     * @param dto
     * @return
     */
    public LoginDTO loginMember(LoginDTO dto){
        String nickname = dto.getNickname();
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        return memberMapper.toLoginDto(member);
    }

    /**
     * 회원등록
     * @param memberDTO
     * @return
     */
    public MemberDTO registerMember(MemberDTO memberDTO) {
        Member member = memberMapper.toMember(memberDTO);
        Member savedMember = memberRepository.save(member);
        return memberMapper.toMemberDTO(savedMember);
    }


    /**
     * 회원 정보 출력
     * @param mNo
     * @return
     */
    public MemberDTO viewMember(Long mNo){
        Member member = memberRepository.findById(mNo).orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));
        return memberMapper.toMemberDTO(member);
    }
}
