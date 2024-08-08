package gift.member.exception;

import gift.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum MemberErrorType implements ErrorType {
    MEMBER_NOT_FOUND_EXCEPTION("M404_001", HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다"),
    INVALID_CREDENTIAL_EXCEPTION("M401_001", HttpStatus.UNAUTHORIZED, "이메일이나 비밀번호가 틀렸습니다"),
    EMAIL_ALREADY_EXISTS_EXCEPTION("M409_001", HttpStatus.CONFLICT, "이미 존재하는 이메일입니다");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    MemberErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
