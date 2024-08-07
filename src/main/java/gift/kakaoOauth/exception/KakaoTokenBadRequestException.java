package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class KakaoTokenBadRequestException extends BusinessException {
    public KakaoTokenBadRequestException(){
        super(KakaoOauthErrorType.KAKAO_TOKEN_BAD_REQUEST_EXCEPTION);
    }
}
