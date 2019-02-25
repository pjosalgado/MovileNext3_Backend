package tech.paulosalgado.ifoodorder.application.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.paulosalgado.ifoodorder.application.exception.GenericServiceException;

@ControllerAdvice
@RestController
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductCreationException.class)
    public ResponseEntity<GenericServiceException> handleProductCreationException(ProductCreationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new GenericServiceException("invalid_input", exception.getMessage()));
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<GenericServiceException> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new GenericServiceException("not_found", exception.getMessage()));
    }

}
