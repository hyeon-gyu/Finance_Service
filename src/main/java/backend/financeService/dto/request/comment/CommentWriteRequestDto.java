package backend.financeService.dto.request.comment;


import backend.financeService.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentWriteRequestDto {

    private String content;
    private String nickname;
    private String password;
    private Boolean blind; // 비밀 댓글 여부

    @Builder
    public static Comment ofEntity(CommentWriteRequestDto commentWriteRequestDto, PasswordEncoder passwordEncoder){
        return Comment.builder()
                .content(commentWriteRequestDto.getContent())
                .nickname(commentWriteRequestDto.getNickname())
                //.password(commentWriteRequestDto.getPassword())
                .password(passwordEncoder.encode(commentWriteRequestDto.getPassword()))
                .blind(commentWriteRequestDto.getBlind())
                .build();
    }
}
