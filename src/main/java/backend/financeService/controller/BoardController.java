package backend.financeService.controller;


import backend.financeService.dto.request.board.BoardEditRequestDto;
import backend.financeService.dto.request.board.BoardUpdateRequestDto;
import backend.financeService.dto.request.board.BoardWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.dto.response.board.BoardListResponseDto;
import backend.financeService.dto.response.board.BoardSimpleResponseDto;
import backend.financeService.service.community.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    /** 게시글 목록 조회하기 */
    @GetMapping("/list")    // list?page=1
    public ResponseEntity<Page<BoardListResponseDto>> list(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<BoardListResponseDto> boardListResponseDtos = boardService.list(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(boardListResponseDtos);
    }

    /** 게시글 상세보기 */
    @GetMapping("/read/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> read(
            @PathVariable(name = "boardId") Long boardId)
    {
        BoardDetailResponseDto findBoard = boardService.readPost(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(findBoard);
    }

    /** 게시글 작성하기 */
    @PostMapping("/write")
    public ResponseEntity<BoardDetailResponseDto> write(@RequestBody BoardWriteRequestDto boardWriteRequestDto){
        BoardDetailResponseDto writeBoard = boardService.writePost(boardWriteRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(writeBoard);
    }

    /** 게시글 수정하기 */
    // 게시글 수정하기 버튼 눌렀을 때 ( 비밀번호도 함께 )
    @PostMapping("/pwdCheck/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> pwdCheck(@PathVariable(name = "boardId") Long boardId,
                                                           @RequestBody BoardEditRequestDto boardEditRequestDto){
        BoardDetailResponseDto boardDetailResponseDto = boardService.pwdCheck(boardId, boardEditRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardDetailResponseDto);
    }

    // 게시글 수정 완료 버튼 눌렀을 때
    @PostMapping("/update/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> update(@PathVariable(name = "boardId")Long boardId,
                                                              @RequestBody BoardUpdateRequestDto boardUpdateRequestDto){
        BoardDetailResponseDto boardDetailResponseDto = boardService.updatePost(boardId, boardUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardDetailResponseDto);
    }

    /** 게시글 삭제하기 */ // 비밀번호와 같아야 삭제 가능
    @PostMapping("/delete/{boardId}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "boardId")Long boardId,
                                        @RequestBody BoardEditRequestDto boardEditRequestDto){
        BoardSimpleResponseDto boardSimpleResponseDto = boardService.deletePost(boardId, boardEditRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardSimpleResponseDto);
    }






}
