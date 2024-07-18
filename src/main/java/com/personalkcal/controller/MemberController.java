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
        Member member = memberMapper.toMember(memberDTO);
        kcalService.calculateKcalForMember(member);
        memberDTO = memberMapper.toMemberDTO(member);
        return ResponseEntity.ok(memberDTO);
    }

}
