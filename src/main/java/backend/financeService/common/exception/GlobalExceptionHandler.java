package backend.financeService.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /** exception handler 정리 */
    // not found exception이 발생하면 동작시킬 핸들러 정의
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ErrorResponse> nullPointerExceptionHandler(BoardNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse("BAD REQUEST", "NOT FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 비밀번호 불일치 에러 핸들러
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectPwdException.class)
    public ResponseEntity<backend.financeService.common.exception.ErrorResponse> IncorrectPwdExceptionHandler(IncorrectPwdException e){
        ErrorResponse errorResponse = new ErrorResponse("BAD REQUEST", "NOT SAME", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 환율 정보 api에 문제 발생시 에러 핸들러
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExchangeRateException.class)
    public ResponseEntity<ErrorResponse> ExchangeRateApiExceptionHandler(ExchangeRateException e){
        ErrorResponse errorResponse = new ErrorResponse("BAD REQUEST", "EXCHANGE RATE API ERROR", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
