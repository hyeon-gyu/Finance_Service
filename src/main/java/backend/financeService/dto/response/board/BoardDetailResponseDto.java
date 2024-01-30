package backend.financeService.dto.response.board;


import backend.financeService.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class BoardDetailResponseDto {

    // 게시글 작성 완료 버튼 누르면 -> 곧장 홈화면으로 리다이렉트 되는 것이 아닌 작성한 글 세부사항을 보여주게 하는게 나아보임(게시글 정보 전부 줘야함)
    private Long boardId;
    private String nickname;
    private String title;
    private String content;
    private String createDate;


    public BoardDetailResponseDto(Long boardId, String nickname, String title, String content, String createDate) {
        this.boardId = boardId;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }

    public static BoardDetailResponseDto fromEntity(Board board){
        return BoardDetailResponseDto.builder()
                .boardId(board.getId())
                .nickname(board.getNickname())
                .title(board.getTitle())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .build();
    }

}
