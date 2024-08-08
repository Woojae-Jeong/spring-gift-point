package gift.order.exception;

import gift.exception.BusinessException;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(){
        super(OrderErrorType.ORDER_NOT_FOUND_EXCEPTION);
    }
}
