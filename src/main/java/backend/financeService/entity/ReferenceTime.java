package backend.financeService.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class ReferenceTime {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_id")
    private Long id;

    private String referenceTime;
    private String bankName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ref_id") // 단방향 다대일 , 외래키 걸기
    private List<ExchangeRate> exchangeRateList = new ArrayList<>();

    @Builder
    public ReferenceTime(String referenceTime, String bankName, List<ExchangeRate> exchangeRateList) {
        this.referenceTime = referenceTime;
        this.bankName = bankName;
        this.exchangeRateList = exchangeRateList;
    }
}
