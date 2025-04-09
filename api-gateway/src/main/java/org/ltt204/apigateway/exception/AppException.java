package org.ltt204.apigateway.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppException extends RuntimeException {
    ErrorCode error;
    String detail;

    public AppException(ErrorCode error, String message) {
        super(error.getMessage());
        this.error = error;
        this.detail = message;
    }
}
