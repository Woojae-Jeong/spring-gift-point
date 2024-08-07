package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class KaKaoServerErrorException extends BusinessException {
    public KaKaoServerErrorException(String message) {
        super(message);
    }
}
