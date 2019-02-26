package tech.paulosalgado.ifoodorder.infrastructure.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerNotFoundException;

@ControllerAdvice
@RestController
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CustomerCreationException.class)
    public ResponseEntity<GenericServiceException> handleCustomerCreationException(CustomerCreationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new GenericServiceException("invalid_input", exception.getMessage()));
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<GenericServiceException> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new GenericServiceException("not_found", exception.getMessage()));
    }

}
