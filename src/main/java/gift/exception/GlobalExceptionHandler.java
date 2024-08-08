package gift.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ErrorType errorType = commonErrorType.METHOD_ARGUMENT_NOT_VALID_EXCEPTION;
        ErrorResponse response = new ErrorResponse(errorType.getErrorCode(), errorType.getHttpStatus(), errorType.getMessage());
        return new ResponseEntity<>(response, errorType.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ErrorType errorType = commonErrorType.MISSING_PATH_VARIABLE_EXCEPTION;
        ErrorResponse response = new ErrorResponse(errorType.getErrorCode(), errorType.getHttpStatus(), errorType.getMessage());
        return new ResponseEntity<>(response, errorType.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ErrorType errorType = commonErrorType.MISSING_REQUEST_PARAM_EXCEPTION;
        ErrorResponse response = new ErrorResponse(errorType.getErrorCode(), errorType.getHttpStatus(), errorType.getMessage());
        return new ResponseEntity<>(response, errorType.getHttpStatus());
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorType errorType = e.getErrorType();
        ErrorResponse response = new ErrorResponse(errorType.getErrorCode(), errorType.getHttpStatus(), errorType.getMessage());
        return new ResponseEntity<>(response, errorType.getHttpStatus());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e) {
        ErrorType errorType = commonErrorType.NO_SUCH_ARGUMENT_EXCEPTION;
        ErrorResponse response = new ErrorResponse(errorType.getErrorCode(), errorType.getHttpStatus(), errorType.getMessage());
        return new ResponseEntity<>(response, errorType.getHttpStatus());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorType errorType = commonErrorType.ILLEGAL_ARGUMENT_EXCEPTION;
        ErrorResponse response = new ErrorResponse(errorType.getErrorCode(), errorType.getHttpStatus(), errorType.getMessage());
        return new ResponseEntity<>(response, errorType.getHttpStatus());
    }
}

