package backend.financeService.dto.request.board;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardUpdateRequestDto {

    // 수정은 제목, 내용만 가능하다!
    private String content;
    private String title;
}
