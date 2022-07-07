package pl.employee.jdbc.rest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseApiException {

    public BadRequestException(String message, String code) {
        super(message, code);
    }

    public BadRequestException(String message, String code, String details) {
        super(message, code, details);
    }
}
