package gift.wish.exception;

import gift.exception.BusinessException;

public class WishNotFoundException extends BusinessException {
    public WishNotFoundException() {
        super(WishErrorType.WISH_NOT_FOUND_EXCEPTION);
    }
}
