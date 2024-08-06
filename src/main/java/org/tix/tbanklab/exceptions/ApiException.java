package org.tix.tbanklab.exceptions;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super("Yandex API Error caused by: " + message);
    }
}
