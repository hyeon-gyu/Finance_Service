package backend.financeService.dto.request.board;

import backend.financeService.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardWriterRequestDto {

    private String title;
    private String nickname;
    private String content;
    private String pwd;

    // dto -> entity
    public static Board ofEntity(BoardWriterRequestDto boardWriterRequestDto){
        return Board.builder()
                .title(boardWriterRequestDto.getTitle())
                .nickname(boardWriterRequestDto.getNickname())
                .content(boardWriterRequestDto.getContent())
                .pwd(boardWriterRequestDto.getPwd())
                .commentCnt(0)
                .build();
    }

}
