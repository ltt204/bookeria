package org.ltt204.apigateway.dto.request.token;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IntrospectRequestDto {
    String token;
}
