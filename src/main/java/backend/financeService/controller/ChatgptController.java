package backend.financeService.controller;


import backend.financeService.dto.request.openAI.QuestionRequestDto;
import backend.financeService.dto.response.openAI.ChatgptResponseDto;
import backend.financeService.service.openai.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fintech/chat")
@RequiredArgsConstructor
public class ChatgptController {

    private final ChatgptService chatgptService;

    @PostMapping("/")
    public ResponseEntity<ChatgptResponseDto> question(@RequestBody QuestionRequestDto questionRequestDto){
        ChatgptResponseDto answer = chatgptService.question(questionRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }
}
