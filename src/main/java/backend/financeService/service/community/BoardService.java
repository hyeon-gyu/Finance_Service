package backend.financeService.service.community;

import backend.financeService.common.exception.NotFoundException;
import backend.financeService.common.exception.DeleteException;
import backend.financeService.common.exception.IncorrectPwdException;
import backend.financeService.config.PwdEncoderConfig;
import backend.financeService.dto.request.board.BoardEditRequestDto;
import backend.financeService.dto.request.board.BoardUpdateRequestDto;
import backend.financeService.dto.request.board.BoardWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.dto.response.board.BoardListResponseDto;
import backend.financeService.dto.response.board.BoardSimpleResponseDto;
import backend.financeService.entity.Board;
import backend.financeService.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 : 단방향 암호화


    /** (공통) 비밀번호 확인 절차 */
    public void postPwdCheck(Board existingBoard,String pwd){
//        if (!(existingBoard.getPassword().equals(pwd))){
        if(!passwordEncoder.matches(pwd, existingBoard.getPassword())){
            throw new IncorrectPwdException("비밀번호가 일치하지 않습니다.");
        }
    }

    /** (공통) 게시글 존재 유무 확인 절차 */
    public Board findPost(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(
                () -> new NotFoundException("게시글을 찾을 수 없습니다. boardId = " + boardId));
        // exception 터지면 리턴 못하고 global exception handler가 highjacking하여 error response 진행시작
    }

    /** 게시글 목록 조회 */
    public Page<BoardListResponseDto> list(Pageable pageable){
        Page<Board> boardList = boardRepository.findAll(pageable);
        List<BoardListResponseDto> boardListResponseDtos = boardList.stream().map(BoardListResponseDto::fromEntity).toList();
        return new PageImpl<>(boardListResponseDtos, pageable, boardList.getTotalElements());
    }

    /** 게시글 상세보기 (읽기) */
    public BoardDetailResponseDto readPost(Long boardId){
        Board findBoard = findPost(boardId);
        return BoardDetailResponseDto.fromEntity(findBoard);
    }

    /** 게시글 작성 */
    public BoardDetailResponseDto writePost(BoardWriteRequestDto boardWriteRequestDto){
        // dto -> entity
        Board newBoard = BoardWriteRequestDto.ofEntity(boardWriteRequestDto, passwordEncoder);
        Board saveBoard = boardRepository.save(newBoard);
        return BoardDetailResponseDto.fromEntity(saveBoard);
    }

    /** 게시글 수정 비밀번호 확인 절차 */
    public BoardDetailResponseDto updateCheck(Long boardId, BoardEditRequestDto boardEditRequestDto){
        Board existingBoard = findPost(boardId);
        String password = boardEditRequestDto.getPwd();
        postPwdCheck(existingBoard, password);
        return BoardDetailResponseDto.fromEntity(existingBoard);
    }

    /** 게시글 수정 */
    @Transactional
    public BoardDetailResponseDto updatePost(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board existingBoard = findPost(boardId);
        existingBoard.updateBoard(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return BoardDetailResponseDto.fromEntity(existingBoard);
    }

    /** 게시글 삭제 */
    @Transactional
    public BoardSimpleResponseDto deletePost(Long boardId, BoardEditRequestDto boardEditRequestDto){
        Board findBoard = findPost(boardId);
        postPwdCheck(findBoard, boardEditRequestDto.getPwd());
        try{
            boardRepository.deleteById(boardId);
            return BoardSimpleResponseDto.fromEntity(findBoard);
        }catch (Exception e){
            throw new DeleteException("삭제 도중 에러가 발생했습니다.");
        }
    }
}
