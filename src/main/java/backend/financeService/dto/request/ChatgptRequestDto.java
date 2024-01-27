package backend.financeService.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * chat gpt에게 request 보낼 dto
 * */

@Data
public class ChatgptRequestDto implements Serializable {

    private String model;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;
    private Boolean stream;
    private String message;

    @Builder
    public ChatgptRequestDto(String model, Integer maxTokens, Double temperature, Double topP, Boolean stream, String message) {
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
        this.stream = stream;
        this.message = message;
    }
}
