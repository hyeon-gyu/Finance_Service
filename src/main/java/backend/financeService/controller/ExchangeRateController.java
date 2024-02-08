package backend.financeService.controller;


import backend.financeService.dto.response.exchange_rate.ExchangeRateResponseDto;
import backend.financeService.service.exchange.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fintech")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/exchangeRate")
    public ResponseEntity<List<ExchangeRateResponseDto>> getExchangeRate() {
        List<ExchangeRateResponseDto> exchangeRate = exchangeRateService.getExchangeRate();
        return ResponseEntity.status(HttpStatus.OK).body(exchangeRate);
    }
}
