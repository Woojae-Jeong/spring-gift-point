package gift.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String errorCdoe;
    private int httpStatus;
    private String message;

    public ErrorResponse(String errorCdoe, HttpStatus httpStatus, String message) {
        this.errorCdoe = errorCdoe;
        this.httpStatus = httpStatus.value();
        this.message = message;
    }

    public String getErrorCdoe() {
        return errorCdoe;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
