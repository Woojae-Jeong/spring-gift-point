package gift.option.exception;

import gift.exception.BusinessException;

public class OptionNotFoundException extends BusinessException {
    public OptionNotFoundException(String message){
        super(message);
    }
}
