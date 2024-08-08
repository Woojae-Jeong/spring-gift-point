package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class KakaoToken4xxException extends BusinessException {
    public KakaoToken4xxException(){
        super(KakaoOauthErrorType.KAKAO_TOKEN_4XX_EXCEPTION);
    }
}
