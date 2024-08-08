package gift.exception.TokenException;

import gift.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException() {
        super(TokenErrorType.INVALID_TOKEN_EXCEPTION);
    }
}