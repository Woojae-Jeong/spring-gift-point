package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class KaKaoServerErrorException extends BusinessException {
    public KaKaoServerErrorException(){
        super(KakaoOauthErrorType.KAKAO_SERVER_ERROR_EXCEPTION);
    }
}
