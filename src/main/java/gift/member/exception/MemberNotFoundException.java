package gift.member.exception;

import gift.exception.BusinessException;

public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
