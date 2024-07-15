package com.personalkcal.controller;

import com.personalkcal.Dto.LoginDTO;
import com.personalkcal.Dto.MemberDTO;
import com.personalkcal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/login")
    public ResponseEntity<LoginDTO> loginMember(@RequestBody LoginDTO loginDTO) {
        LoginDTO loggedInMember = memberService.loginMember(loginDTO);
        return ResponseEntity.ok(loggedInMember);
    }

    @PostMapping("/register")
    public ResponseEntity<MemberDTO> registerMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO registeredMember = memberService.registerMember(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredMember);
    }


    @GetMapping("/{mNo}")
    public ResponseEntity<MemberDTO> viewMember(@PathVariable Long mNo) {
        MemberDTO memberDTO = memberService.viewMember(mNo);
        return ResponseEntity.ok(memberDTO);
    }

}
