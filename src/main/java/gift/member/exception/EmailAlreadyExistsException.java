package gift.member.exception;

import gift.exception.BusinessException;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException() {
        super(MemberErrorType.EMAIL_ALREADY_EXISTS_EXCEPTION);
    }
}
