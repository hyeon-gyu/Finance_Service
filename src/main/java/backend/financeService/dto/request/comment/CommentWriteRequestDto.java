package backend.financeService.dto.request.comment;


import backend.financeService.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentWriteRequestDto {

    private String content;

    @Builder
    public static Comment ofEntity(CommentWriteRequestDto commentWriteRequestDto){
        return Comment.builder()
                .content(commentWriteRequestDto.getContent())
                .build();
    }
}
