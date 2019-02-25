package tech.paulosalgado.ifoodorder.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GenericServiceException {

    private String type;
    private String message;

}
