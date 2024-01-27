package backend.financeService.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * gpt에게 답변 받을 dto
 * */

@Data
public class ChatgptResponseDto {

    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;

    @Builder
    public ChatgptResponseDto(String id, String object, long created, String model, List<Choice> choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
    }
}
