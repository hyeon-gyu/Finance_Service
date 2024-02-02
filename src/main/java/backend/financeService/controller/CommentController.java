package backend.financeService.controller;


import backend.financeService.dto.request.comment.CommentWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fintech/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> write(
            @PathVariable(name = "boardId") Long boardId
            ,@RequestBody CommentWriteRequestDto commentWriteRequestDto){
        BoardDetailResponseDto boardDetailResponseDto = commentService.write(boardId, commentWriteRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardDetailResponseDto);
    }

}
