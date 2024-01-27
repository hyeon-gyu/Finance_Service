package backend.financeService.controller;


import backend.financeService.dto.request.QuestionRequestDto;
import backend.financeService.dto.response.ChatgptResponseDto;
import backend.financeService.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ChatgptController {

    private final ChatgptService chatgptService;

    @PostMapping("/chat")
    public ResponseEntity<?> question(@RequestBody QuestionRequestDto question){
        ChatgptResponseDto answer = chatgptService.question(question);
        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }
}
