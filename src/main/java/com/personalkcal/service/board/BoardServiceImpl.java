package com.personalkcal.service.board;

import com.personalkcal.domain.Board;
import com.personalkcal.domain.Member;
import com.personalkcal.dto.board.BoardRequest.*;
import com.personalkcal.dto.board.BoardResponse.*;
import com.personalkcal.exception.board.NotFoundBoardException;
import com.personalkcal.exception.member.NotFoundMemberException;
import com.personalkcal.repository.BoardRepository;
import com.personalkcal.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시글 작성
     * @param request
     * @return
     */
    @Override
    public WriteBoardResponse writeBoard(WriteBoardRequest request){
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new NotFoundMemberException("해당 회원을 찾을 수 없습니다."));

        Board board = Board.builder()
                .title(request.title())
                .content(request.content())
                .member(member)
                .viewCount(0)
                .build();

        Board save = boardRepository.save(board);
        return new WriteBoardResponse(save);
    }

    /**
     * 게시판 리스트
     * @param pageable
     * @return
     */
    @Override
    public Page<BoardListResponse> boardList(Pageable pageable){
        return boardRepository.findAll(pageable).map(BoardListResponse::new);
    }

    /**
     * 게시글 상세보기
     * @param boardId
     * @return
     */
    @Override
    public ViewBoardResponse viewBoard(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new NotFoundBoardException("게시글이 존재하지 않습니다."));
        board.addViewCount(board.getViewCount());
        return new ViewBoardResponse(board);
    }

    /**
     * 게시글 수정
     * @param boardId
     * @param request
     * @return
     */
    @Override
    public UpdateBoardResponse updateBoard(Long boardId, UpdateBoardRequest request){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new NotFoundBoardException("게시글이 존재하지 않습니다."));
        board.updateBoard(request.title(),request.content());
        Board update = boardRepository.save(board);
        return new UpdateBoardResponse(update);
    }

    /**
     * 게시글 삭제
     * @param id
     */
    @Override
    public void deleteBoard(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new NotFoundBoardException("게시글이 존재하지 않습니다."));
        boardRepository.delete(board);
    }

}
