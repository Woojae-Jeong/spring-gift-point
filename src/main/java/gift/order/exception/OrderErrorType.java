package gift.order.exception;

import gift.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum OrderErrorType implements ErrorType {
    ORDER_NOT_FOUND_EXCEPTION("Order404_001", HttpStatus.NOT_FOUND, "해당 주문이 존재하지 않습니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    OrderErrorType(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
