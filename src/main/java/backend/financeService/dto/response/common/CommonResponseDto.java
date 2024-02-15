package backend.financeService.dto.response.common;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class CommonResponseDto {


    private String message;

    public CommonResponseDto(String message) {
        this.message = message;
    }


}
