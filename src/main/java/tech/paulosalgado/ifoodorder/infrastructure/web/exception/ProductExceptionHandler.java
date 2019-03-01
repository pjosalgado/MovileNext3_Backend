package tech.paulosalgado.ifoodorder.infrastructure.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductNotFoundException;

@ControllerAdvice
@RestController
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductCreationException.class)
    public ResponseEntity<WebServiceException> handleProductCreationException(ProductCreationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new WebServiceException("invalid_input", exception.getMessage()));
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<WebServiceException> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new WebServiceException("not_found", exception.getMessage()));
    }

}
