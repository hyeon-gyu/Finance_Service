package backend.financeService.controller;


import backend.financeService.dto.request.board.BoardModifyRequestDto;
import backend.financeService.dto.request.board.BoardUpdateRequestDto;
import backend.financeService.dto.request.board.BoardWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//
//@RestControllerAdvice
@RestController
@RequestMapping("/fintech/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /** 게시글 조회하기 */
//    @GetMapping("/list")    // list?page=1&size=10
//    public ResponseEntity<?> list(){
//
//    }

    /** 게시글 작성하기 */
    @PostMapping("/write")
    public ResponseEntity<BoardDetailResponseDto> write(@RequestBody BoardWriteRequestDto boardWriteRequestDto){
        BoardDetailResponseDto writeBoard = boardService.write(boardWriteRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(writeBoard);
    }

    /** 게시글 수정하기 */
    // 게시글 수정하기 버튼 눌렀을 때 ( 비밀번호도 함께 )
    @PostMapping("/pwdCheck/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> pwdCheck(@PathVariable(name = "boardId") Long boardId,
                                                           @RequestBody BoardModifyRequestDto boardModifyRequestDto){
        BoardDetailResponseDto boardDetailResponseDto = boardService.pwdCheck(boardId, boardModifyRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardDetailResponseDto);
    }

    // 게시글 수정 완료 버튼 눌렀을 때
    @PostMapping("/update/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> update(@PathVariable(name = "boardId")Long boardId,
                                                              @RequestBody BoardUpdateRequestDto boardUpdateRequestDto){
        BoardDetailResponseDto boardDetailResponseDto = boardService.update(boardId, boardUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardDetailResponseDto);

    }




}
