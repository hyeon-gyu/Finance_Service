package backend.financeService.dto.request;


import lombok.Data;

import java.io.Serializable;

/**
 * 사용자로부터 request 받을 dto
 * */

@Data
public class QuestionRequestDto implements Serializable {

    private String question;
}
