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
     * 여성 닉네임/스펙 입력
     * @param height
     * @param weight
     * @param age
     * @return
     */
    public Member createWoman(int height, int weight, int age,String nickname){
        Member woman = Member.specOfWoman(height,weight,age,nickname);
        return memberRepository.save(woman);
    }

    /**
     * 남성 닉네임/스펙 입력
     * @param height
     * @param weight
     * @param age
     * @return
     */
    public Member createMan(int height, int weight,int age,String nickname){
        Member man = Member.specOfMan(height, weight, age,nickname);
        return memberRepository.save(man);
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
