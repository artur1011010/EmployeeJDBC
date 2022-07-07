package pl.employee.jdbc.rest.exception;

import lombok.Getter;

@Getter
public abstract class BaseApiException extends RuntimeException{
    protected String code;
    protected String details;

    public BaseApiException(String message, String code) {
        super(message);
        this.code = code;
    }

    public BaseApiException(String message, String code, String details) {
        super(message);
        this.code = code;
        this.details = details;
    }
}
