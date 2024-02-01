package backend.financeService.dto.response.board;


import backend.financeService.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardListResponseDto {

    // 목록 조회 할 때는 게시글 번호, 제목, 닉네임, 작성시간, 댓글 갯수
    private Long boardId;
    private String title;
    private String nickname;
    private String modifiedDate;
    private int commentCnt;

    @Builder
    public BoardListResponseDto(Long boardId, String title, String nickname, String modifiedDate, int commentCnt) {
        this.boardId = boardId;
        this.title = title;
        this.nickname = nickname;
        this.modifiedDate = modifiedDate;
        this.commentCnt = commentCnt;
    }

    public static BoardListResponseDto fromEntity(Board board){
        return BoardListResponseDto.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .nickname(board.getNickname())
                .modifiedDate(board.getModifiedDate())
                .commentCnt(board.getCommentCnt())
                .build();
    }
}
