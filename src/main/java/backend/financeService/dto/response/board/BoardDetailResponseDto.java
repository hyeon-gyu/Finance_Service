package backend.financeService.dto.response.board;


import backend.financeService.dto.response.comment.CommentDetailResponseDto;
import backend.financeService.entity.Board;
import backend.financeService.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Builder
public class BoardDetailResponseDto {

    // 게시글 작성 완료 버튼 누르면 -> 곧장 홈화면으로 리다이렉트 되는 것이 아닌 작성한 글 세부사항을 보여주게 하는게 나아보임(게시글 정보 전부 줘야함)
    private Long boardId;
    private String nickname;
    private String title;
    private String content;
    private String createdDate;

    // 댓글리스트도 함께 전달
    private List<CommentDetailResponseDto> commentList;

    public BoardDetailResponseDto(Long boardId, String nickname, String title, String content, String createdDate, List<CommentDetailResponseDto> commentList) {
        this.boardId = boardId;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.commentList = commentList;
    }

    public static BoardDetailResponseDto fromEntity(Board board){
        return BoardDetailResponseDto.builder()
                .boardId(board.getId())
                .nickname(board.getNickname())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .commentList(board.getCommentList().stream().map(CommentDetailResponseDto::fromEntity).collect(Collectors.toList()))
                // comment entity -> comment response dto로 변환
                .build();
    }
}
