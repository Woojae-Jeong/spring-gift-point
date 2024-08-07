package gift.category.exception;

import gift.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum CategoryErrorType implements ErrorType {
    CATEGORY_NOT_FOUND_EXCEPTION("C404001", HttpStatus.NOT_FOUND, "해당 카테고리를 찾을 수 없습니다.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    CategoryErrorType(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getErrorCode(){
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
