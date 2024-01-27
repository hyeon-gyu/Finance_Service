package backend.financeService.service;


import backend.financeService.config.ChatgptConfig;
import backend.financeService.dto.request.ChatgptRequestDto;
import backend.financeService.dto.request.QuestionRequestDto;
import backend.financeService.dto.response.ChatgptResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:openai.yml")
@Service
public class ChatgptService {

    private static RestTemplate restTemplate = new RestTemplate();

    @Value("${api-key}")
    private String apiKey;

    //http 헤더 정보 생성
    public HttpEntity<ChatgptRequestDto> buildHttpEntity(ChatgptRequestDto requestDto){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(ChatgptConfig.MEDIA_TYPE));
        httpHeaders.add(ChatgptConfig.AUTHORIZATION, ChatgptConfig.BEARER + apiKey);
        return new HttpEntity<>(requestDto, httpHeaders);
    }

    public ChatgptResponseDto getResponse(HttpEntity<ChatgptRequestDto> chatgptRequestDtoHttpEntity){
        ResponseEntity<ChatgptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatgptConfig.CHAT_URL,
                chatgptRequestDtoHttpEntity,
                ChatgptResponseDto.class);
        return responseEntity.getBody();
    }

    public ChatgptResponseDto question(QuestionRequestDto requestDto){
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatgptRequestDto(
                                ChatgptConfig.CHAT_MODEL,
                                ChatgptConfig.MAX_TOKEN,
                                ChatgptConfig.TEMPERATURE,
                                ChatgptConfig.TOP_P,
                                ChatgptConfig.STREAM,
                                requestDto.getQuestion()
                        )
                )
        );

    }


}
