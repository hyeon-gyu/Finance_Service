package backend.financeService.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer result; // 조회결과
    private String cur_unit; // 통화코드
    private String cur_nm; // 국가,통화명
    private String ttb; // (전신환)송금 받으실 때
    private String tts; // (전신환)송금 보내실 때
    private String deal_bas_r; // 매매 기준율
    private String bkpr; // 장부가격
    private String yy_efee_r; //년환가료율
    private String ten_dd_efee_r; // 10일환가료율
    private String kftc_deal_bas_r; // 서울외국환중개 매매기준율
    private String kftc_bkpr; // 서울외국환중개 장부가격

    public ExchangeRate(Integer result, String cur_Unit, String cur_nm, String ttb, String tts, String deal_bas_r, String bkpr, String yy_efee_r, String ten_dd_efee_r, String kftc_deal_bas_r, String kftc_bkpr) {
        this.result = result;
        this.cur_unit = cur_Unit;
        this.cur_nm = cur_nm;
        this.ttb = ttb;
        this.tts = tts;
        this.deal_bas_r = deal_bas_r;
        this.bkpr = bkpr;
        this.yy_efee_r = yy_efee_r;
        this.ten_dd_efee_r = ten_dd_efee_r;
        this.kftc_deal_bas_r = kftc_deal_bas_r;
        this.kftc_bkpr = kftc_bkpr;
    }
}
