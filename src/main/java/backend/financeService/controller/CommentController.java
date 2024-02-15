package backend.financeService.controller;


import backend.financeService.dto.request.comment.CommentDeleteRequestDto;
import backend.financeService.dto.request.comment.CommentWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.dto.response.common.CommonResponseDto;
import backend.financeService.service.community.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fintech/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    /** 댓글 서비스 이용 과정
     *  닉네임과 비밀번호를 입력하고 댓글을 작성할 수 있다.
     * */

    /** 댓글 작성 */
    @PostMapping("write/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> writeComment(
            @PathVariable(name = "boardId") Long boardId
            ,@RequestBody CommentWriteRequestDto commentWriteRequestDto){
        BoardDetailResponseDto boardDetailResponseDto = commentService.writeComment(boardId, commentWriteRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardDetailResponseDto);
    }

    /** 댓글 삭제 */
    @PostMapping("delete/{commentId}")
    public ResponseEntity<CommonResponseDto> deleteComment(
            @PathVariable(name = "commentId") Long commentId,
            @RequestBody CommentDeleteRequestDto commentDeleteRequestDto){
        CommonResponseDto commonResponseDto = commentService.deleteComment(commentId, commentDeleteRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponseDto);
    }



}
