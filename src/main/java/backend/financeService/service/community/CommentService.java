package backend.financeService.service.community;

import backend.financeService.common.exception.BoardNotFoundException;
import backend.financeService.dto.request.comment.CommentWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.entity.Board;
import backend.financeService.entity.Comment;
import backend.financeService.repository.BoardRepository;
import backend.financeService.repository.CommentRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final BoardService boardService;


    public BoardDetailResponseDto write(Long boardId, CommentWriteRequestDto commentWriteRequestDto){
        Board findBoard = boardService.findPost(boardId);
        Comment newComment = CommentWriteRequestDto.ofEntity(commentWriteRequestDto);
        findBoard.addComment(newComment); // board, comment 둘다 정보 기입
        commentRepository.save(newComment); // 지연로딩 때문에 이거 작성 안하면 댓글작성후 게시글 dto 리턴될 때 금방 쓴 댓글은 null로 들어감!
        return BoardDetailResponseDto.fromEntity(findBoard);
    }

}
