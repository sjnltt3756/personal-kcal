package com.personalkcal.service.implementation;

import com.personalkcal.domain.Board;
import com.personalkcal.domain.Member;
import com.personalkcal.domain.Reply;
import com.personalkcal.dto.reply.ReplyRequest.*;
import com.personalkcal.dto.reply.ReplyResponse.*;
import com.personalkcal.exception.board.NotFoundBoardException;
import com.personalkcal.exception.member.NotFoundMemberException;
import com.personalkcal.exception.reply.NotFoundReplyException;
import com.personalkcal.repository.BoardRepository;
import com.personalkcal.repository.MemberRepository;
import com.personalkcal.repository.ReplyRepository;
import com.personalkcal.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public WriteReplyResponse writeReply(WriteReplyRequest request){
        Board board = boardRepository.findById(request.boardId())
                .orElseThrow(()-> new NotFoundBoardException("게시글이 존재하지 않습니다."));
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));
        Reply reply = Reply.builder()
                .reply(request.reply())
                .member(member)
                .board(board)
                .build();
        Reply save = replyRepository.save(reply);
        return new WriteReplyResponse(save);
    }

    @Override
    public UpdateReplyResponse updateReply(Long id, UpdateReplyRequest request){
        Reply reply = replyRepository.findById(id)
                .orElseThrow(()-> new NotFoundReplyException("댓글이 존재하지 않습니다."));

        Reply updateReply = Reply.builder()
                .reply(request.reply())
                .member(reply.getMember())
                .board(reply.getBoard())
                .build();
        Reply update = replyRepository.save(updateReply);
        return new UpdateReplyResponse(update);
    }

    @Override
    public void deleteReply(Long id){
        Reply reply = replyRepository.findById(id)
                .orElseThrow(()-> new NotFoundReplyException("댓글이 존재하지 않습니다."));
        replyRepository.delete(reply);
    }

}
