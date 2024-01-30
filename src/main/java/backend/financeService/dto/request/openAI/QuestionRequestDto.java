package backend.financeService.dto.request.openAI;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 사용자로부터 request 받을 dto
 * */

@Data
@NoArgsConstructor
public class QuestionRequestDto implements Serializable {

    private String question;
}
