package gift.exception;

import org.springframework.http.HttpStatus;

public interface ErrorType {
    String getErrorCode();
    HttpStatus getHttpStatus();
    String getMessage();
}
