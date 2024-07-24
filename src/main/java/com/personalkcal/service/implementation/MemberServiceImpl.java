package com.personalkcal.service.implementation;

import com.personalkcal.domain.Member;
import com.personalkcal.dto.member.MemberResponse.*;
import com.personalkcal.dto.member.MemberRequest.*;
import com.personalkcal.exception.member.ExistMemberException;
import com.personalkcal.exception.member.NotFoundMemberException;
import com.personalkcal.repository.MemberRepository;
import com.personalkcal.service.KcalService;
import com.personalkcal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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
        String nickname = request.nickname();
        findMemberByNickname(nickname);
        Member member = memberRepository.findByNickname(nickname).get();
        return new LoginResponse(member);
    }

    /**
     * 회원등록
     * @param request
     * @return
     */
    @Override
    public RegisterResponse registerMember(RegisterRequest request) {
        validateExistLoginNickname(request);
        Member member = Member.builder()
                .nickname(request.nickname())
                .gender(request.gender())
                .height(request.height())
                .weight(request.weight())
                .age(request.age())
                .build();

        // 회원 저장
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
        Member view = memberRepository.save(memberKcal);
        return new ViewResponse(view);
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
        Member updatedMember = Member.builder()
                .id(member.getId())
                .nickname(request.nickname() != null ? request.nickname() : member.getNickname())
                .gender(member.getGender())
                .height(request.height() != 0 ? request.height() : member.getHeight())
                .weight(request.weight() != 0 ? request.weight() : member.getWeight())
                .age(request.age() != 0 ? request.age() : member.getAge())
                .build();

        Member savedMember = memberRepository.save(updatedMember);

        return new UpdateResponse(savedMember);
    }

    private void validateExistLoginNickname(RegisterRequest request) {
        Optional<Member> findMembers = memberRepository.findByNickname(request.nickname());
        if (findMembers.isPresent()) {
            throw new ExistMemberException("이미 존재하는 닉네임입니다.");
        }
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
