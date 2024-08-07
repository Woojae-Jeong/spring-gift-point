package gift.member.exception;

import gift.exception.BusinessException;

public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException() {
        super(MemberErrorType.MEMBER_NOT_FOUND_EXCEPTION);
    }
}
