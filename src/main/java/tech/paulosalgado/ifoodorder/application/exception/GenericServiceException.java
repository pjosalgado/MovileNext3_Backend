package tech.paulosalgado.ifoodorder.application.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GenericServiceException {

    private String type;
    private String message;

}
