package gift.option.exception;

import gift.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum OptionErrorType implements ErrorType {
    OPTION_NOT_FOUND_EXCEPTION("Option404_001", HttpStatus.NOT_FOUND, "해당 옵션이 존재하지 않습니다."),
    OPTION_VALIDATION_EXCEPTION("Option409_001", HttpStatus.CONFLICT, "동일한 이름을 가진 옵션이 존재합니다"),
    SINGLE_OPTION_REMOVAL_EXCEPTION("Option409_002", HttpStatus.CONFLICT, "상품 내에 옵션이 적어도 한 개는 있어야 합니다." );

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    OptionErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
