package com.personalkcal.controller;

import com.personalkcal.dto.reply.ReplyRequest.*;
import com.personalkcal.dto.reply.ReplyResponse.*;
import com.personalkcal.service.reply.ReplyServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
@CrossOrigin("/localhost:3000")
public class ReplyController {

    private final ReplyServiceImpl replyService;

    @PostMapping("/write")
    public ResponseEntity<?> writeReply(@Valid @RequestBody WriteReplyRequest request) {
        WriteReplyResponse response = replyService.writeReply(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReply(@Valid @PathVariable Long id, @RequestBody UpdateReplyRequest request) {
        UpdateReplyResponse response = replyService.updateReply(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReply(@Valid @PathVariable Long id) {
        replyService.deleteReply(id);
        return ResponseEntity.ok("댓글 삭제 완료");
    }
}
