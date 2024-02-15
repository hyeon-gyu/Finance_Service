package backend.financeService.dto.request.board;

import backend.financeService.config.PwdEncoderConfig;
import backend.financeService.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteRequestDto {

    private String title;
    private String nickname;
    private String content;
    private String pwd;

    // dto -> entity
    public static Board ofEntity(BoardWriteRequestDto boardWriteRequestDto, PasswordEncoder passwordEncoder){
        return Board.builder()
                .title(boardWriteRequestDto.getTitle())
                .nickname(boardWriteRequestDto.getNickname())
                .content(boardWriteRequestDto.getContent())
                //.password(boardWriteRequestDto.getPwd())
                .password(passwordEncoder.encode(boardWriteRequestDto.getPwd())) // pwd 암호화 과정 주입
                .commentCnt(0)
                .build();
    }
}
