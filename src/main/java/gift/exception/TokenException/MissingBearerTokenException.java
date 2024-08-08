package gift.exception.TokenException;

import gift.exception.BusinessException;

public class MissingBearerTokenException extends BusinessException {
    public MissingBearerTokenException(){
        super(TokenErrorType.MISSING_BEARER_TOKEN_EXCEPTION);
    }
}
