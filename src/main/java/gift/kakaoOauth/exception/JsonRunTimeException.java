package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class JsonRunTimeException extends BusinessException {
    public JsonRunTimeException(){
        super(KakaoOauthErrorType.JASON_RUN_TIME_EXCEPTION);
    }
}
