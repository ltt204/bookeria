package org.ltt204.profileservice.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppException extends RuntimeException {
    ErrorCode error;

    public AppException(ErrorCode error, String detail) {
        super(detail);
        this.error = error;
    }
}
