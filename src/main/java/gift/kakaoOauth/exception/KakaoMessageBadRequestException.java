package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class KakaoMessageBadRequestException extends BusinessException {

    public KakaoMessageBadRequestException() {
        super(KakaoOauthErrorType.KAKAO_SEND_MESSAGE_TO_ME_BAD_REQUEST_EXCEPTION);
    }
}
