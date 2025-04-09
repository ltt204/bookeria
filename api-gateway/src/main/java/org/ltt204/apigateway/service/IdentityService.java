package org.ltt204.apigateway.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.ltt204.apigateway.client.IdentityClient;
import org.ltt204.apigateway.dto.request.token.IntrospectRequestDto;
import org.ltt204.apigateway.dto.response.common.ApplicationResponseDto;
import org.ltt204.apigateway.dto.response.token.IntrospectResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;

    public Mono<ApplicationResponseDto<IntrospectResponseDto>> introspect(String token) {
        // Simulate token introspection
        // In a real application, you would call the identity service to validate the token
        return identityClient.introspect(
                IntrospectRequestDto.builder()
                        .token(token)
                        .build()
        );
    }
}
