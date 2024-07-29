package com.personalkcal.service.member;

import com.personalkcal.domain.Member;
import com.personalkcal.dto.member.MemberResponse.*;
import com.personalkcal.dto.member.MemberRequest.*;
import com.personalkcal.exception.member.ExistMemberException;
import com.personalkcal.exception.member.NotFoundMemberException;
import com.personalkcal.repository.MemberRepository;
import com.personalkcal.service.Kcal.KcalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final KcalService kcalService;

    /**
     * 닉네임으로 로그인
     * @param request
     * @return
     */
    public LoginResponse loginMember(LoginRequest request) {
        Member member = findMemberByNickname(request.nickname());
        return new LoginResponse(member);
    }

    /**
     * 회원등록
     * @param request
     * @return
     */
    @Override
    public RegisterResponse registerMember(RegisterRequest request) {
        validateExistNickname(request.nickname());
        Member member = Member.builder()
                .nickname(request.nickname())
                .gender(request.gender())
                .height(request.height())
                .weight(request.weight())
                .age(request.age())
                .build();

        Member savedMember = memberRepository.save(member);

        return new RegisterResponse(savedMember);
    }


    /**
     * 회원 정보 출력
     * @param id
     * @return
     */
    @Override
    public ViewResponse viewMember(Long id) {
        Member member = findMemberById(id);
        Member memberKcal = kcalService.calculateKcalForMember(member);
        return new ViewResponse(memberKcal);
    }

    /**
     * 회원 정보 수정
     * @param id
     * @param request
     * @return
     */
    @Override
    public UpdateResponse updateMember(Long id, UpdateRequest request) {
        Member member = findMemberById(id);
        validateExistNickname(request.nickname());
        member.updateMember(request.nickname(),request.height(),request.weight(),request.age());
        Member savedMember = memberRepository.save(member);
        return new UpdateResponse(savedMember);
    }

    /**
     * 회원 삭제
     * @param id
     */
    @Override
    public void deleteMember(Long id){
        Member member = findMemberById(id);
        memberRepository.delete(member);
    }


    private void validateExistNickname(String nickname) {
        memberRepository.findByNickname(nickname)
                .ifPresent(member -> {
                    throw new ExistMemberException("이미 존재하는 닉네임입니다.");
                });

    }

    private Member findMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));
    }

    private Member findMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));
    }

}
