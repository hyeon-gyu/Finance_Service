package backend.financeService.common.exception;

public class DeleteException extends RuntimeException{

    public DeleteException() {
    }

    public DeleteException(String message) {
        super(message);
    }
}
