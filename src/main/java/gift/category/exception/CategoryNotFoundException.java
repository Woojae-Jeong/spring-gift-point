package gift.category.exception;

import gift.exception.BusinessException;

public class CategoryNotFoundException extends BusinessException {

    public CategoryNotFoundException() {
        super(CategoryErrorType.CATEGORY_NOT_FOUND_EXCEPTION);
    }
}
