package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class KakaoUserInfoBadRequestException extends BusinessException {
    public KakaoUserInfoBadRequestException(){
        super(KakaoOauthErrorType.KAKAO_USER_INFO_BAD_REQUEST_EXCEPTION);
    }
}
