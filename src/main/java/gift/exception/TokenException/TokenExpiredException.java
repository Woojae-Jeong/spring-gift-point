package gift.exception.TokenException;

import gift.exception.BusinessException;

public class TokenExpiredException extends BusinessException {
    public TokenExpiredException() {
        super(TokenErrorType.TOKEN_EXPIRED_EXCEPTION);
    }
}
