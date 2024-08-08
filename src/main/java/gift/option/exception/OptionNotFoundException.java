package gift.option.exception;

import gift.exception.BusinessException;

public class OptionNotFoundException extends BusinessException {
    public OptionNotFoundException(){
        super(OptionErrorType.OPTION_NOT_FOUND_EXCEPTION);
    }
}
