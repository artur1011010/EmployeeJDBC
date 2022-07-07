package pl.employee.jdbc.rest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.employee.jdbc.rest.dto.ApiErrorResponse;
import pl.employee.jdbc.rest.exception.BadRequestException;
import pl.employee.jdbc.rest.exception.BaseApiException;
import pl.employee.jdbc.rest.exception.NotFoundException;

@ControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<ApiErrorResponse> handleNotFoundException(final BaseApiException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createErrorMessage(ex));
    }

    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<ApiErrorResponse> handleBadRequestException(final BaseApiException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorMessage(ex));
    }

    private ApiErrorResponse createErrorMessage(final BaseApiException ex) {
        return ApiErrorResponse.builder()
                .message(ex.getMessage())
                .code(ex.getCode())
                .details(ex.getDetails())
                .build();
    }
}
