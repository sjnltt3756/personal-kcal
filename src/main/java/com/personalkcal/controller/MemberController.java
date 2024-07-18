package com.personalkcal.controller;

import com.personalkcal.Dto.LoginDTO;
import com.personalkcal.Dto.MemberDTO;
import com.personalkcal.Dto.RegisterDTO;
import com.personalkcal.domain.Member;
import com.personalkcal.mapper.MemberMapper;
import com.personalkcal.service.implementation.KcalServiceImpl;
import com.personalkcal.service.implementation.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@CrossOrigin("/localhost:3000")
public class MemberController {

    private final MemberServiceImpl memberService;
    private final KcalServiceImpl kcalService;
    private final MemberMapper memberMapper;

    @PostMapping("/login")
    public ResponseEntity<MemberDTO> loginMember(@RequestBody LoginDTO loginDTO) {
        MemberDTO loggedInMember = memberService.loginMember(loginDTO);
        return ResponseEntity.ok(loggedInMember);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> registerMember(@RequestBody RegisterDTO registerDTO) {
        RegisterDTO registeredMember = memberService.registerMember(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredMember);
    }


    @GetMapping("/view/{id}")
    public ResponseEntity<MemberDTO> viewMember(@PathVariable(name = "id") Long id) {
        MemberDTO memberDTO = memberService.viewMember(id);
        // MemberDTO를 Member 엔티티로 매핑
        Member member = memberMapper.toMember(memberDTO);

        // 회원의 칼로리 정보를 계산하여 설정합니다.
        kcalService.calculateKcalForMember(member);

        // Member 엔티티를 다시 MemberDTO로 매핑하여 반환합니다.
        memberDTO = memberMapper.toMemberDTO(member);
        return ResponseEntity.ok(memberDTO);
    }

}
