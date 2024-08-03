package com.personalkcal.controller.member;

import com.personalkcal.dto.member.MemberRequest.*;
import com.personalkcal.dto.member.MemberResponse.*;
import com.personalkcal.service.Kcal.KcalServiceImpl;
import com.personalkcal.service.member.MemberServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;
    private final KcalServiceImpl kcalService;

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@Valid @RequestBody LoginRequest request) {
        LoginResponse loggedInMember = memberService.loginMember(request);
        return ResponseEntity.ok(loggedInMember);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse registeredMember = memberService.registerMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredMember);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewMember(@Valid @PathVariable(name = "id") Long id) {
        ViewResponse viewedMember = memberService.viewMember(id);
        return ResponseEntity.ok(viewedMember);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMember(@Valid @PathVariable(name="id")Long id, @RequestBody UpdateRequest request){
        UpdateResponse updatedMember = memberService.updateMember(id, request);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@Valid @PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.ok("회원삭제 완료");
    }
}
