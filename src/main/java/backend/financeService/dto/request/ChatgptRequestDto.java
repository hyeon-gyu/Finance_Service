package backend.financeService.dto.request;


import backend.financeService.config.ChatgptConfig;
import backend.financeService.dto.response.ChatgptResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * chat GPT에게 요청할 REQUEST DTO
 * */

@Data
@NoArgsConstructor
public class ChatgptRequestDto implements Serializable {

    private String model;
    private Integer maxTokens;
    private Double temperature;
    private Double top_p;
    private Boolean stream;
    private String messages;

    @Builder
    public static ChatCompletionRequest of(QuestionRequestDto requestDto){
        return ChatCompletionRequest.builder()
                .model(ChatgptConfig.CHAT_MODEL)
                .messages(convertChatMessage(requestDto))
                .maxTokens(ChatgptConfig.MAX_TOKEN)
                .temperature(ChatgptConfig.TEMPERATURE)
                .topP(ChatgptConfig.TOP_P)
                .build();
    }

    private static List<ChatMessage> convertChatMessage(QuestionRequestDto request) {
        return List.of(new ChatMessage(ChatgptConfig.ROLE, request.getQuestion()));
    }
}

//    @Builder
//    public ChatgptRequestDto(String model, Integer maxTokens, Double temperature, Double top_p, Boolean stream, List<ChatMessage> messages) {
//        this.model = model;
//        this.maxTokens = maxTokens;
//        this.temperature = temperature;
//        this.top_p = top_p;
//        this.stream = stream;
//        this.messages = messages;
//    }


