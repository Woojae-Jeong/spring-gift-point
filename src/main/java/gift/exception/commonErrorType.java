package gift.exception;

import org.springframework.http.HttpStatus;

public enum commonErrorType implements ErrorType{
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION("Request400_001", HttpStatus.BAD_REQUEST, "요청값이 잘못됐습니다. API 명세서 양식에 맞춰주세요"),
    ILLEGAL_ARGUMENT_EXCEPTION("Request400_002", HttpStatus.BAD_REQUEST, "요청값이 잘못됐습니다. 요구사항에 맞게 작성해주세요."),
    NO_SUCH_ARGUMENT_EXCEPTION("Request400_003", HttpStatus.NOT_FOUND, "요구하신 요청에 맞는 리소스를 찾을 수 없습니다.");

    private String errorCode;
    private HttpStatus httpStatus;
    private String message;

    commonErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
