package org.ltt204.apigateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.ltt204.apigateway.client.IdentityClient;
import org.ltt204.apigateway.dto.request.token.IntrospectRequestDto;
import org.ltt204.apigateway.dto.response.common.ApplicationResponseDto;
import org.ltt204.apigateway.exception.AppException;
import org.ltt204.apigateway.exception.ErrorCode;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthGlobalFilter implements GlobalFilter {

    IdentityClient identityClient;
    ObjectMapper objectMapper;

    private final String[] EXCLUDED_PATHS = {
            "/identity/auth/.*",
            "/identity/users/signup",
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("AuthGlobalFilter start");
        log.info("Request path {}", exchange.getRequest().getURI().getPath());
        if (Arrays.stream(EXCLUDED_PATHS).anyMatch(path -> exchange.getRequest().getURI().getPath().matches(path))) {
            return chain.filter(exchange);
        }

        HttpHeaders headers = exchange.getRequest().getHeaders();

        if (CollectionUtils.isEmpty(headers)) {
            return unauthenticated(exchange.getResponse());
        }

        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        token = token.replace("Bearer ", "");

        // Call the identity service to validate the token
        return identityClient.introspect(
                IntrospectRequestDto.builder()
                        .token(token)
                        .build()
        ).flatMap(
                response -> {
                    if (response.isSuccess()) {
                        return chain.filter(exchange);
                    }

                    // If the token is valid, continue with the request
                    return unauthenticated(exchange.getResponse());
                }
        ).onErrorResume(e -> {
                    log.error("Error during token introspection: {}", e.getMessage());
                    return unauthenticated(exchange.getResponse());
                }
        );
    }

    private Mono<Void> unauthenticated(ServerHttpResponse response) {
        ApplicationResponseDto<?> applicationResponseDto = ApplicationResponseDto.failure(ErrorCode.UNAUTHENTICATED);

        String body;
        try {
            body = objectMapper.writeValueAsString(applicationResponseDto);
        } catch (JsonProcessingException e) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return response.writeWith(Mono.just(
                response.bufferFactory().wrap(body.getBytes())
        ));
    }
}
