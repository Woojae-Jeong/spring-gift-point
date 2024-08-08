package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class KakaoUserInfo4xxException extends BusinessException {
    public KakaoUserInfo4xxException(){
        super(KakaoOauthErrorType.KAKAO_USER_INFO_4XX_EXCEPTION);
    }
}
