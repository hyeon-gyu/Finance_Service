package backend.financeService.service.exchange;


import backend.financeService.common.exception.ExchangeRateException;
import backend.financeService.dto.response.exchange_rate.ExchangeRateResponseDto;
import backend.financeService.entity.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 환율 정보를 제공하는 서비스 클래스.
 */

@Service
@Transactional
@RequiredArgsConstructor
@PropertySource("classpath:exchangeRate.yml")
public class ExchangeRateService {

    /** https://www.koreaexim.go.kr/ir/HPHKIR020M01?apino=2&viewtype=C&searchselect=&searchword=   링크 참고하여 api 형식 확인*/

    @Value("${api-url}")
    private String apiUrl;
    @Value("${authkey}")
    private String authKey;

    public List<ExchangeRateResponseDto> getExchangeRate() {
        LocalDateTime currentDatetime = LocalDateTime.now();
        String searchDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(currentDatetime);
        String fullUrl = apiUrl + "?authkey=" + authKey + "&searchdate=" + searchDate + "&data=AP01";

        RestTemplate restTemplate = new RestTemplate();
        List<ExchangeRateResponseDto> response =  new ArrayList<>();

        try{
            ExchangeRate[] result = restTemplate.getForObject(fullUrl, ExchangeRate[].class);
            if (result != null){
                response = Arrays.stream(result)
                        .map(ExchangeRateResponseDto::fromEntity).collect(Collectors.toList());
                return response;
            }
            else { // api 응답이 null일 때
                throw new ExchangeRateException("API 응답이 NULL 입니다.");
            }
        } catch (RestClientException e){
            throw new ExchangeRateException("RestClientException 발생했습니다.");
        }
    }
}
