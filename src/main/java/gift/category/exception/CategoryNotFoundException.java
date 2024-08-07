package gift.category.exception;

import gift.exception.BusinessException;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
