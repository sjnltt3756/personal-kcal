package com.personalkcal.service.implementation;

import com.personalkcal.dto.member.LoginDTO;
import com.personalkcal.dto.member.MemberDTO;
import com.personalkcal.dto.member.RegisterDTO;
import com.personalkcal.dto.member.UpdateDTO;
import com.personalkcal.domain.Member;
import com.personalkcal.repository.MemberRepository;
import com.personalkcal.service.KcalService;
import com.personalkcal.service.MemberService;
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
     * @param dto
     * @return
     */
    @Override
    public MemberDTO loginMember(LoginDTO dto) {
        String nickname = dto.nickname();
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        return new MemberDTO(member); // 레코드 생성자를 사용하여 MemberDTO 객체 생성
    }

    /**
     * 회원등록
     * @param registerDto
     * @return
     */
    @Override
    public RegisterDTO registerMember(RegisterDTO registerDto) {
        // RegisterDTO를 Member로 변환
        Member member = new Member(
                null,
                registerDto.nickname(),
                registerDto.gender(),
                registerDto.height(),
                registerDto.weight(),
                registerDto.age(),
                null

        );

        // 회원 저장
        Member savedMember = memberRepository.save(member);

        // 저장된 Member를 RegisterDTO로 변환하여 반환
        return new RegisterDTO(savedMember);
    }


    /**
     * 회원 정보 출력
     * @param id
     * @return
     */
    @Override
    public MemberDTO viewMember(Long id) {
        // 회원을 ID로 조회
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

        // 회원의 칼로리 정보 계산
        Member member1 = kcalService.calculateKcalForMember(member);
        Member view = memberRepository.save(member1);
        // MemberDTO 레코드 생성자를 사용하여 변환
        return new MemberDTO(view);
    }

    /**
     * 회원 정보 수정
     * @param id
     * @param updateDTO
     * @return
     */
    @Override
    public MemberDTO updateMember(Long id, UpdateDTO updateDTO) {
        // 회원을 ID로 조회
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

        // UpdateDTO의 값을 사용하여 Member 객체를 업데이트
        Member updatedMember = new Member(
                member.getId(),
                updateDTO.nickname() != null ? updateDTO.nickname() : member.getNickname(),
                member.getGender(),
                updateDTO.height() != null ? updateDTO.height() : member.getHeight(),
                updateDTO.weight() != null ? updateDTO.weight() : member.getWeight(),
                updateDTO.age() != null ? updateDTO.age() : member.getAge(),
                member.getKcal() // 칼로리 정보는 변경하지 않음
        );

        // 업데이트된 회원을 데이터베이스에 저장
        Member savedMember = memberRepository.save(updatedMember);

        // 저장된 Member를 MemberDTO로 변환하여 반환
        return new MemberDTO(savedMember);
    }

}
