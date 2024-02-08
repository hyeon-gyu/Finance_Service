package backend.financeService;

import backend.financeService.repository.BoardRepository;
import backend.financeService.service.community.BoardService;
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
