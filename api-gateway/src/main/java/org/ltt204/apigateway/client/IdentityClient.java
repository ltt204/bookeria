package org.ltt204.apigateway.client;

import org.ltt204.apigateway.dto.request.token.IntrospectRequestDto;
import org.ltt204.apigateway.dto.response.common.ApplicationResponseDto;
import org.ltt204.apigateway.dto.response.token.IntrospectResponseDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

@Component
public interface IdentityClient {
    @PostExchange(url = "/identity/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApplicationResponseDto<IntrospectResponseDto>> introspect(
            @RequestBody IntrospectRequestDto dto
    );
}
