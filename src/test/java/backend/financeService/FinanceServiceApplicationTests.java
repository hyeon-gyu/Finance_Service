package backend.financeService;

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
class FinanceServiceApplicationTests {

	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardRepository boardRepository;


}
