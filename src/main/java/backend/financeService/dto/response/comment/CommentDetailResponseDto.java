package backend.financeService.dto.response.comment;


import backend.financeService.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDetailResponseDto {

    private Long commentId;
    private String content;
    private String createdDate;

    @Builder
    public CommentDetailResponseDto(Long commentId, String content, String createdDate) {
        this.commentId = commentId;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static CommentDetailResponseDto fromEntity(Comment comment){
        return CommentDetailResponseDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .build();
    }

}
