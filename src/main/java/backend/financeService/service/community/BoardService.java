package backend.financeService.service.community;

import backend.financeService.common.exception.BoardNotFoundException;
import backend.financeService.common.exception.IncorrectPwdException;
import backend.financeService.dto.request.board.BoardModifyRequestDto;
import backend.financeService.dto.request.board.BoardUpdateRequestDto;
import backend.financeService.dto.request.board.BoardWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.dto.response.board.BoardListResponseDto;
import backend.financeService.entity.Board;
import backend.financeService.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /** 게시글 목록 조회 */
    public Page<BoardListResponseDto> list(Pageable pageable){
        Page<Board> boardList = boardRepository.findAll(pageable);
        List<BoardListResponseDto> boardListResponseDtos = boardList.stream().map(BoardListResponseDto::fromEntity).toList();
        return new PageImpl<>(boardListResponseDtos, pageable, boardList.getTotalElements());
    }

    /** 게시글 상세보기 (읽기) */
    public BoardDetailResponseDto read(Long boardId){
        Board findBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("게시글을 찾을 수 없습니다. boardId = " + boardId));
        return BoardDetailResponseDto.fromEntity(findBoard);
    }

    /** 게시글 작성 */
    public BoardDetailResponseDto write(BoardWriteRequestDto boardWriteRequestDto){
        // dto -> entity
        Board newBoard = BoardWriteRequestDto.ofEntity(boardWriteRequestDto);
        Board saveBoard = boardRepository.save(newBoard);
        return BoardDetailResponseDto.fromEntity(saveBoard);
    }

    /** 게시글 수정 비밀번호 확인 절차 */
    public BoardDetailResponseDto pwdCheck(Long boardId, BoardModifyRequestDto boardModifyRequestDto){
        Board existingBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("게시글을 찾을 수 없습니다. boardId = " + boardId));
        if (!(existingBoard.getPwd().equals(boardModifyRequestDto.getPwd()))){
            throw new IncorrectPwdException("비밀번호가 일치하지 않습니다.");
        }
        return BoardDetailResponseDto.fromEntity(existingBoard);
    }

    /** 게시글 수정 */
    public BoardDetailResponseDto update(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        // finding board
        Board existingBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new BoardNotFoundException("게시글을 찾을 수 없습니다. boardId = " + boardId));
        existingBoard.updateBoard(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return BoardDetailResponseDto.fromEntity(existingBoard);
    }
}