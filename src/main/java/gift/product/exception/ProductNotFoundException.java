package gift.product.exception;

import gift.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(){
        super(ProductErrorType.PRODUCT_NOT_FOUND_EXCEPTION);
    }
}
