package gift.exception.TokenException;

import gift.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum TokenErrorType implements ErrorType {
    INVALID_TOKEN_EXCEPTION("Token401_001", HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰입니다"),
    MISSING_BEARER_TOKEN_EXCEPTION("Token400_001", HttpStatus.BAD_REQUEST, "토큰에 Bearer 접두사가 빠졌습니다."),
    NULL_TOKEN_EXCEPTION("Token400_002", HttpStatus.BAD_REQUEST, "null 토큰입니다."),
    TOKEN_EXPIRED_EXCEPTION("Token401_002", HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    TokenErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
