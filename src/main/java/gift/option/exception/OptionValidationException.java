package gift.option.exception;

import gift.exception.BusinessException;

public class OptionValidationException extends BusinessException {
    public OptionValidationException(){
        super(OptionErrorType.OPTION_VALIDATION_EXCEPTION);
    }
}
