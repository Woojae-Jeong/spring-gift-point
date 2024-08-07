package gift.kakaoOauth.exception;

import gift.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum KakaoOauthErrorType implements ErrorType {
    KAKAO_USER_INFO_BAD_REQUEST_EXCEPTION("K400_001",HttpStatus.BAD_REQUEST, "카카오 정보 가져오기 API : BadRquest 발생"),
    KAKAO_TOKEN_BAD_REQUEST_EXCEPTION("K400_002", HttpStatus.BAD_REQUEST, "카카오 토큰 가져오기 API : BadRquest 발생"),
    KAKAO_SEND_MESSAGE_TO_ME_BAD_REQUEST_EXCEPTION("K400_003", HttpStatus.BAD_REQUEST, "카카오 정보 가져오기 API : BadRquest 발생"),
    JASON_RUN_TIME_EXCEPTION("K500_001", HttpStatus.INTERNAL_SERVER_ERROR, "카카오 나에게 메세지 보내기 API : Json 객체 변환 중 에러 발생"),
    KAKAO_SERVER_ERROR_EXCEPTION("K502_001", HttpStatus.BAD_GATEWAY, "카카오 API : 카카오 서버 에러 발생");


    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    KakaoOauthErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
