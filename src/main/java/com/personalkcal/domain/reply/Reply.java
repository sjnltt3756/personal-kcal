package com.personalkcal.domain.reply;

import com.personalkcal.domain.board.Board;
import com.personalkcal.domain.member.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_ID")
    private Long id;

    @NotBlank(message = "댓글을 입력해주세요.")
    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Reply(String reply, Board board, Member member){
        this.reply = reply;
        this.board = board;
        this.member = member;
    }

    public void updateReply(String reply){
        this.reply = reply;
    }




}
