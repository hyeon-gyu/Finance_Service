package backend.financeService.board;

import backend.financeService.dto.request.board.BoardEditRequestDto;
import backend.financeService.dto.request.board.BoardWriteRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.dto.response.board.BoardListResponseDto;
import backend.financeService.repository.BoardRepository;
import backend.financeService.service.community.BoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        BoardDetailResponseDto savedBoard = boardService.writePost(boardWriteRequestDto);
        //then
        Assertions.assertNotNull(savedBoard.getBoardId());
        Assertions.assertEquals("제목1",savedBoard.getTitle());
        Assertions.assertEquals("이름1",savedBoard.getNickname());
        Assertions.assertEquals("내용1",savedBoard.getContent());
    }

    @Test
    public void modifyTest(){
        BoardEditRequestDto boardEditRequestDto = new BoardEditRequestDto("a1234");
        BoardDetailResponseDto boardDetailResponseDto = boardService.updateCheck(1L, boardEditRequestDto);
        Assertions.assertEquals("질문이요.",boardDetailResponseDto.getTitle());
    }

    @Test
    public void listTest(){
        Pageable pageable = Pageable.ofSize(10).withPage(1);
        Page<BoardListResponseDto> result = boardService.list(pageable);
        Assertions.assertEquals(10,result.getTotalElements());
    }

    @Test
    public void readTest(){
        Logger logger = LoggerFactory.getLogger(BoardDetailResponseDto.class);
        BoardDetailResponseDto read = boardService.readPost(1L);

        String logMessage = String.join(", ",
                "BoardId: " + read.getBoardId(),
                "Nickname: " + read.getNickname(),
                "Title: " + read.getTitle(),
                "Content: " + read.getContent(),
                "CreateDate: " + read.getCreatedDate()
        );
        logger.info(logMessage);
    }
}
