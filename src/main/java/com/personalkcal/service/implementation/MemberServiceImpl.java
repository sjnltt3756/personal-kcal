package com.personalkcal.service.implementation;

import com.personalkcal.Dto.LoginDTO;
import com.personalkcal.Dto.MemberDTO;
import com.personalkcal.Dto.RegisterDTO;
import com.personalkcal.Dto.UpdateDTO;
import com.personalkcal.mapper.MemberMapper;
import com.personalkcal.domain.Member;
import com.personalkcal.repository.MemberRepository;
import com.personalkcal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;


    /**
     * 닉네임으로 로그인
     * @param dto
     * @return
     */
    @Override
    public MemberDTO loginMember(LoginDTO dto){
        String nickname = dto.getNickname();
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        return memberMapper.toMemberDTO(member);
    }

    /**
     * 회원등록
     * @param registerDto
     * @return
     */
    @Override
    public RegisterDTO registerMember(RegisterDTO registerDto) {
        Member member = memberMapper.toMemberRegister(registerDto);
        Member savedMember = memberRepository.save(member);
        return memberMapper.toRegisterDTO(savedMember);
    }


    /**
     * 회원 정보 출력
     * @param mNo
     * @return
     */
    @Override
    public MemberDTO viewMember(Long mNo){
        Member member = memberRepository.findById(mNo).orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));
        return memberMapper.toMemberDTO(member);
    }

    /**
     * 회원 정보 수정
     * @param id
     * @param updateDTO
     * @return
     */
    @Override
    public MemberDTO updateMember(Long id, UpdateDTO updateDTO){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));
        memberMapper.updateDTOToMember(updateDTO, member);
        Member updatedMember = memberRepository.save(member);
        return memberMapper.toMemberDTO(updatedMember);
    }

}
