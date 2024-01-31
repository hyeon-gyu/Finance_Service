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
        ErrorResponse errorResponse = new ErrorResponse("BAD REQUEST", "NOT FOUND");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 비밀번호 불일치 에러 핸들러
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectPwdException.class)
    public ResponseEntity<backend.financeService.common.exception.ErrorResponse> IncorrectPwdExceptionHandler(IncorrectPwdException e){
        ErrorResponse errorResponse = new ErrorResponse("BAD REQUEST", "NOT SAME");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
