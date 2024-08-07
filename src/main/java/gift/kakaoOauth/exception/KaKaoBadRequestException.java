package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class KaKaoBadRequestException extends BusinessException {
    public KaKaoBadRequestException(String message){
        super(message);
    }
}
