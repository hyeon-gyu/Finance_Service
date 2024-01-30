package backend.financeService.controller;


import backend.financeService.dto.request.board.BoardWriterRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fintech/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 조회하기
     * */
//    @GetMapping("/list")    // list?page=1&size=10
//    public ResponseEntity<?> list(){
//
//    }

    /**
     * 게시글 작성하기
     * */
    @PostMapping("/write")
    public ResponseEntity<BoardDetailResponseDto> write(@RequestBody BoardWriterRequestDto boardWriterRequestDto){
        BoardDetailResponseDto writeBoard = boardService.write(boardWriterRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(writeBoard);
    }

}
