package backend.financeService.dto.response.exchange_rate;


import backend.financeService.entity.ExchangeRate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeRateResponseDto {


    private Integer result; // 조회결과
    private String cur_unit; // 통화코드
    private String cur_nm; // 국가,통화명
    private String ttb; // (전신환)송금 받으실 때
    private String tts; // (전신환)송금 보내실 때
    private String deal_bas_r; // 매매 기준율

    @Builder
    public ExchangeRateResponseDto(Integer result, String cur_unit, String cur_nm, String ttb, String tts, String deal_bas_r) {
        this.result = result;
        this.cur_unit = cur_unit;
        this.cur_nm = cur_nm;
        this.ttb = ttb;
        this.tts = tts;
        this.deal_bas_r = deal_bas_r;
    }

    public static ExchangeRateResponseDto fromEntity(ExchangeRate exchangeRate){
        return ExchangeRateResponseDto.builder()
                .result(exchangeRate.getResult())
                .cur_unit(exchangeRate.getCur_unit())
                .cur_nm(exchangeRate.getCur_nm())
                .ttb(exchangeRate.getTtb())
                .tts(exchangeRate.getTts())
                .deal_bas_r(exchangeRate.getDeal_bas_r())
                .build();
    }
}
