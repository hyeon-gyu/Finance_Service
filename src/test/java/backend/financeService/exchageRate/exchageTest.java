package backend.financeService.exchageRate;


import backend.financeService.service.exchange.ExchangeRateService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@PropertySource("classpath:exchangeRate.yml")
public class exchageTest {

    @Value("${api-url}")
    private String apiUrl;
    @Value("${authkey}")
    private String authKey;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

//    @Test
//    public void testGetExchangeRate() {
//        // Mocking
//        String searchDate = "20240208";
//        String fullUrl = apiUrl + "?authkey=" + authKey + "&searchdate=" + searchDate + "&data=AP01";
//
//        ExchageRateResponseDto mockExchangeRate = new ExchageRateResponseDto();
//        // Set mock data here...
//
//        Mockito.when(restTemplate.getForObject(fullUrl, ExchangeRate.class))
//                .thenReturn(mockExchangeRate);
//
//        // Test
//        ExchangeRate result = exchangeRateService.getExchangeRate(searchDate);
//
//        // Assertions
//        assertEquals(mockExchangeRate, result);
//    }
}
