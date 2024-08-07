package gift.kakaoOauth.exception;

import gift.exception.BusinessException;

public class JsonRunTimeException extends BusinessException {
    public JsonRunTimeException(String message){
        super(message);
    }
}
