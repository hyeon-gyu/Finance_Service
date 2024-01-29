package backend.financeService.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;

@Configuration
@PropertySource("classpath:openai.yml")
public class ChatgptConfig {

    @Value("${api-key}")
    private String token;

    public static final String ROLE = "user";
    public static final Double TEMPERATURE = 0.6;
    public static final Double TOP_P = 1.0;
    public static final String CHAT_MODEL = "gpt-3.5-turbo";
    public static final Integer MAX_TOKEN = 1000;

    @Bean
    public OpenAiService openAiService(){
        return new OpenAiService(token,Duration.ofSeconds(60));
    }
    // REST API로 구현했을 때 Response가 전체 답변이 입력된 후 호출이 되기에 wait time을 걸어두지 않으면 time out 이슈가 발생 (finishReason = stop)

}
