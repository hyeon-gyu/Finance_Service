package backend.financeService.dto.response.board;


import backend.financeService.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class BoardSimpleResponseDto {

    private Long boardId;


    public BoardSimpleResponseDto(Long boardId) {
        this.boardId = boardId;
    }

    public static BoardSimpleResponseDto fromEntity(Board board){
        return builder().boardId(board.getId()).build();
    }
}
