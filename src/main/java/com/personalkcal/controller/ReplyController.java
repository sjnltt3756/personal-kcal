package com.personalkcal.controller;

import com.personalkcal.dto.reply.ReplyRequest.*;
import com.personalkcal.dto.reply.ReplyResponse.*;
import com.personalkcal.service.implementation.ReplyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyServiceImpl replyService;

    @PostMapping("/write")
    public ResponseEntity<?> writeReply(@RequestBody WriteReplyRequest request) {
        WriteReplyResponse response = replyService.writeReply(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateReply(@PathVariable Long id, @RequestBody UpdateReplyRequest request) {
        UpdateReplyResponse response = replyService.updateReply(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReply(@PathVariable Long id) {
        replyService.deleteReply(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
