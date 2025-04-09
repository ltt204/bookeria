package org.ltt204.apigateway.config;

import org.ltt204.apigateway.client.IdentityClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080") // Replace with your actual base URL
                .build();
    }

    @Bean
    public IdentityClient identityClient(
            WebClient webClient
    ) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient))
                        .build();

        return httpServiceProxyFactory.createClient(IdentityClient.class);
    }
}
