package br.com.api.device.application.common;

import org.springframework.http.HttpStatus;

public enum HTTPStatus {
    OK(HttpStatus.OK),
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    CREATED(HttpStatus.CREATED),
    NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;

    HTTPStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getStatusCode() {
        return httpStatus.value();
    }

    public String getReasonPhrase() {
        return httpStatus.getReasonPhrase();
    }
}
