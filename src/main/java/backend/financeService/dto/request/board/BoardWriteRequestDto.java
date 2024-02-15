package backend.financeService.dto.request.board;

import backend.financeService.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteRequestDto {

    private String title;
    private String nickname;
    private String content;
    private String pwd;

    // dto -> entity
    public static Board ofEntity(BoardWriteRequestDto boardWriteRequestDto){
        return Board.builder()
                .title(boardWriteRequestDto.getTitle())
                .nickname(boardWriteRequestDto.getNickname())
                .content(boardWriteRequestDto.getContent())
                .password(boardWriteRequestDto.getPwd())
                .commentCnt(0)
                .build();
    }
}
