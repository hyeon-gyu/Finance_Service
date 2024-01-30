package backend.financeService.service;

import backend.financeService.dto.request.board.BoardWriterRequestDto;
import backend.financeService.dto.response.board.BoardDetailResponseDto;
import backend.financeService.entity.Board;
import backend.financeService.repository.BoardRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardDetailResponseDto write(BoardWriterRequestDto boardWriterRequestDto){
        // dto -> entity
        Board newBoard = BoardWriterRequestDto.ofEntity(boardWriterRequestDto);
        Board saveBoard = boardRepository.save(newBoard);
        return BoardDetailResponseDto.fromEntity(saveBoard);

    }

}
