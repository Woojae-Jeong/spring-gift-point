package gift.option.exception;

import gift.exception.BusinessException;

public class SingleOptionRemovalException extends BusinessException {
    public SingleOptionRemovalException(){
        super(OptionErrorType.SINGLE_OPTION_REMOVAL_EXCEPTION);
    }
}
