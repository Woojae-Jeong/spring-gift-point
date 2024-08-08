package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class KakaoMessage4xxException extends BusinessException {

    public KakaoMessage4xxException() {
        super(KakaoOauthErrorType.KAKAO_SEND_MESSAGE_TO_ME_4XX_EXCEPTION);
    }
}
