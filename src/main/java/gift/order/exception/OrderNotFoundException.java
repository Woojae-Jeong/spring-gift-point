package gift.order.exception;

import gift.exception.BusinessException;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(String message){
        super(message);
    }
}
