package backend.financeService.service.openai;



import backend.financeService.dto.request.openAI.ChatgptRequestDto;
import backend.financeService.dto.request.openAI.QuestionRequestDto;
import backend.financeService.dto.response.openAI.ChatgptResponseDto;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatgptService {


    private final OpenAiService openAiService;

    public ChatgptResponseDto question(QuestionRequestDto requestDto) {
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(ChatgptRequestDto.of(requestDto));

        return ChatgptResponseDto.of(chatCompletion);
    }
}
//    @Autowired
//    private RestTemplate restTemplate;
//
////    @Autowired // static 변수 설정 -> 싱글톤 빈으로 관리 방식으로 변경
////    public ChatgptService(RestTemplate restTemplate) {
////        this.restTemplate = restTemplate;
////    }
//
//    @Value("${api-key}")
//    private String apiKey;
//
//    //http 헤더 정보 추가 기입 과정
//    public HttpEntity<ChatgptRequestDto> buildHttpEntity(ChatgptRequestDto requestDto){
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.parseMediaType(ChatgptConfig.MEDIA_TYPE));
//        httpHeaders.add(ChatgptConfig.AUTHORIZATION, ChatgptConfig.BEARER + apiKey);
//        return new HttpEntity<>(requestDto, httpHeaders);
//    }
//
//
//    public ChatgptResponseDto getResponse(HttpEntity<ChatgptRequestDto> chatgptRequestDtoHttpEntity){
//
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setConnectTimeout(60000); // 60sec * 1000ms = 1min
//        requestFactory.setReadTimeout(60000);
//        restTemplate.setRequestFactory(requestFactory);
//
//        ResponseEntity<ChatgptResponseDto> responseEntity = restTemplate.postForEntity(
//                ChatgptConfig.CHAT_URL,
//                chatgptRequestDtoHttpEntity,
//                ChatgptResponseDto.class);
//        System.out.println(responseEntity.getBody());
//        return responseEntity.getBody();
//    }
//
//    public ChatgptResponseDto question(QuestionRequestDto requestDto) {
//        List<ChatMessage> messages = new ArrayList<>();
//        messages.add(ChatMessage.builder().role(ChatgptConfig.ROLE).content(requestDto.getQuestion()).build());
//        ChatgptRequestDto chatgptRequestDto = ChatgptRequestDto.builder()
//                .model(ChatgptConfig.CHAT_MODEL)
//                .maxTokens(ChatgptConfig.MAX_TOKEN)
//                .temperature(ChatgptConfig.TEMPERATURE)
//                .top_p(ChatgptConfig.TOP_P)
//                .stream(ChatgptConfig.STREAM)
//                .messages(messages)
//                .build();
//        System.out.println(chatgptRequestDto);
//
//        return this.getResponse(this.buildHttpEntity(chatgptRequestDto));
//    }
