package org.ltt204.apigateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    // 1xxx
    INTERNAL_SERVER_ERROR(1000, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    // 2xxx
    INVALID_ERROR_KEY(2000, "Invalid error key", HttpStatus.INTERNAL_SERVER_ERROR),
    // 4xxx
    UNAUTHENTICATED(4000, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(4001, "You are not authorized for this action", HttpStatus.FORBIDDEN),
    ;

    private long code;
    @Setter
    private String message;
    private HttpStatusCode httpStatusCode;

    public ErrorCode withMessage(String message) {
        this.setMessage(message);
        return this;
    }
}
