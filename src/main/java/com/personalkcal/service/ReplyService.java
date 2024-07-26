package com.personalkcal.service;

import com.personalkcal.dto.reply.ReplyRequest.*;
import com.personalkcal.dto.reply.ReplyResponse.*;

public interface ReplyService {
    WriteReplyResponse writeReply(WriteReplyRequest request);

    UpdateReplyResponse updateReply(Long id, UpdateReplyRequest request);

    void deleteReply(Long id);
}
