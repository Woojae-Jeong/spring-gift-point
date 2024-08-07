package gift.member.exception;

import gift.exception.BusinessException;

public class InvalidCredentialException extends BusinessException{
    public InvalidCredentialException() {
        super(MemberErrorType.INVALID_CREDENTIAL_EXCEPTION);
    }
}
