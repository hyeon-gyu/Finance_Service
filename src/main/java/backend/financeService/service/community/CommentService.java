package backend.financeService.service.community;

import backend.financeService.common.exception.DeleteException;
import backend.financeService.common.exception.IncorrectPwdException;
import backend.financeService.common.exception.NotFoundException;
import backend.financeService.dto.request.comment.CommentDeleteRequestDto;
import backend.financeService.dto.request.comment.CommentWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.dto.response.common.CommonResponseDto;
import backend.financeService.entity.Board;
import backend.financeService.entity.Comment;
import backend.financeService.repository.BoardRepository;
import backend.financeService.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    /** (공통) 댓글 존재 여부 확인 */
    public Comment findComment(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("댓글이 존재하지 않습니다. "));
    }

    /** (공통) 댓글 비밀번호 일치 여부 확인 */
    public void CommentPwdCheck(Comment existingComment,String password){
        if (!(existingComment.getPassword().equals(password))){
            throw new IncorrectPwdException("비밀번호가 일치하지 않습니다.");
        }
    }
    /** 댓글 작성 */
    @Transactional
    public BoardDetailResponseDto writeComment(Long boardId, CommentWriteRequestDto commentWriteRequestDto){
        Board findBoard = boardService.findPost(boardId);
        Comment newComment = CommentWriteRequestDto.ofEntity(commentWriteRequestDto);
        findBoard.addComment(newComment); // board, comment 둘다 정보 기입
        commentRepository.save(newComment); // 지연로딩 때문에 save 과정 없으면 댓글작성후 게시글 dto 리턴될 때 금방 쓴 댓글은 null로 들어감!
        return BoardDetailResponseDto.fromEntity(findBoard);
    }

    /** 댓글 삭제 */
    @Transactional
    public CommonResponseDto deleteComment(Long commentId, CommentDeleteRequestDto commentDeleteRequestDto){
//        Board findPost = boardService.findPost(boardId); // 1. 댓글 달린 게시물 찾기 (댓글 id로 삭제하는 걸로 변경)
        Comment findComment = findComment(commentId); // 2. 삭제하려는 댓글 찾기
        String password = commentDeleteRequestDto.getPassword(); // 3. 사용자가 입력하는 비밀번호 받기
        System.out.println(password);
        CommentPwdCheck(findComment, password); // 4. 댓글 비밀번호와 입력 비밀번호 일치 여부 확인하기
        try{
            commentRepository.deleteById(commentId);
            return new CommonResponseDto("댓글 삭제 성공");
        } catch (Exception e){
            throw new DeleteException("댓글 삭제 에러 발생");
        }
    }



}
