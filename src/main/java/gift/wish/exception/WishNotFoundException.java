package gift.wish.exception;

import gift.exception.BusinessException;

public class WishNotFoundException extends BusinessException {
    public WishNotFoundException(String message) {
        super(message);
    }
}
