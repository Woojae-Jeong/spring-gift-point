package gift.option.exception;

import gift.exception.BusinessException;

public class OptionValidationException extends BusinessException {
    public OptionValidationException(String message){
        super(message);
    }
}
