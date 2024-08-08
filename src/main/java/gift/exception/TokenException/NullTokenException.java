package gift.exception.TokenException;

import gift.exception.BusinessException;

public class NullTokenException extends BusinessException {
    public NullTokenException() {
        super(TokenErrorType.NULL_TOKEN_EXCEPTION);
    }
}
