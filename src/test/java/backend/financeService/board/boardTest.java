package backend.financeService.board;

import backend.financeService.dto.request.board.BoardModifyRequestDto;
import backend.financeService.dto.request.board.BoardWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.repository.BoardRepository;
import backend.financeService.service.BoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class boardTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void writeTest() {
        //given
        BoardWriteRequestDto boardWriteRequestDto = new BoardWriteRequestDto("제목1","이름1","내용1","비밀번호1");
        //when
        BoardDetailResponseDto savedBoard = boardService.write(boardWriteRequestDto);
        //then
        Assertions.assertNotNull(savedBoard.getBoardId());
        Assertions.assertEquals("제목1",savedBoard.getTitle());
        Assertions.assertEquals("이름1",savedBoard.getNickname());
        Assertions.assertEquals("내용1",savedBoard.getContent());
    }

    @Test
    public void modifyTest(){
        BoardModifyRequestDto boardModifyRequestDto = new BoardModifyRequestDto("a1234");
        BoardDetailResponseDto boardDetailResponseDto = boardService.pwdCheck(1L, boardModifyRequestDto);
        Assertions.assertEquals("질문이요.",boardDetailResponseDto.getTitle());
    }
}
