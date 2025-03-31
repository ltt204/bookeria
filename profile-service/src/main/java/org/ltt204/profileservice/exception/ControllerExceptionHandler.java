package org.ltt204.profileservice.exception;

import org.ltt204.profileservice.dto.response.common.ApplicationResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApplicationResponseDto<?>> handlingRuntimeException(RuntimeException exception) {
        var error = ErrorCode.UNCATEGORIZED;
        log.error(String.valueOf(exception.getCause()));
        var response = ApplicationResponseDto.failure(error, exception.getMessage());
        return ResponseEntity.status(error.getHttpStatusCode()).body(response);
    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    ResponseEntity<ApplicationResponseDto<?>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
//        var enumKey = exception.getFieldError().getDefaultMessage();
//        var error = ErrorCode.INVALID_ERROR_KEY;
//
//        try {
//            error = ErrorCode.valueOf(enumKey);
//
//            var constrainViolation = exception.getBindingResult().getAllErrors().get(0).unwrap(ConstraintViolation.class);
//            var attributes = constrainViolation.getConstraintDescriptor().getAttributes();
//            log.info(attributes.toString());
//            error.setMessage(mapAttribute(error.getMessage(), attributes));
//        } catch (IllegalIdentifierException e) {
//            // TODO: Handle later
//        }
//
//        var response = ApplicationResponseDto.failure(error);
//        return ResponseEntity.status(error.getHttpStatusCode()).body(response);
//    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApplicationResponseDto<?>> handlingAppException(AppException exception) {
        var error = exception.getError();
        var response = ApplicationResponseDto.failure(error, exception.getMessage());
        return ResponseEntity.status(error.getHttpStatusCode()).body(response);
    }

    private String mapAttribute(String message, Map<String, Object> params) {
        var minValue = String.valueOf(params.get(MIN_ATTRIBUTE));
        return message.replace("{" + "min" + "}", minValue);
    }
}
