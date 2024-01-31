package backend.financeService.common.exception;

public class IncorrectPwdException extends RuntimeException{

    public IncorrectPwdException() {
    }

    public IncorrectPwdException(String message) {
        super(message);
    }
}
