package gift.product.exception;

import gift.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(String message){
        super(message);
    }
}
