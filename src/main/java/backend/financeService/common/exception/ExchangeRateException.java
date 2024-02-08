package backend.financeService.common.exception;

public class ExchangeRateException extends RuntimeException{

    public ExchangeRateException() {
    }

    public ExchangeRateException(String message) {
        super(message);
    }
}
