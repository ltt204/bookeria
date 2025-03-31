package org.ltt204.profileservice.dto.response.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.ltt204.profileservice.exception.ErrorCode;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationResponseDto<T> {
    @Builder.Default
    long status = 1000;
    @Builder.Default
    String code = "OK";
    String message;
    String detail;

    @Nullable
    T content;

    public static <T> ApplicationResponseDto<T> success(T content, String message) {
        return ApplicationResponseDto.<T>builder()
                .message(message)
                .content(content)
                .build();
    }

    public static <T> ApplicationResponseDto<T> success(T content) {
        return ApplicationResponseDto.<T>builder()
                .content(content)
                .build();
    }

    public static <T> ApplicationResponseDto<T> success(String message) {
        return ApplicationResponseDto.<T>builder()
                .message(message)
                .content(null)
                .build();
    }


    public static <T> ApplicationResponseDto<T> success() {
        return ApplicationResponseDto.<T>builder()
                .content(null)
                .build();
    }

    public static <T> ApplicationResponseDto<T> failure(ErrorCode exception) {
        return ApplicationResponseDto.<T>builder()
                .status(exception.getCode())
                .code(exception.name())
                .message(exception.getMessage())
                .build();
    }

    public static <T> ApplicationResponseDto<T> failure(ErrorCode exception, String detail) {
        return ApplicationResponseDto.<T>builder()
                .status(exception.getCode())
                .code(exception.name())
                .message(exception.getMessage())
                .detail(detail)
                .build();
    }
}