package com.bm.api_gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * Фабрика фильтров Spring Cloud Gateway, которая вставляет проверку JWT через сервис auth-service.
 */
@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder, @Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    /**
     * Возвращает конкретный GatewayFilter, который будет применяться к фильтруемым запросам
     * @param config
     * @return необходимый фильтр
     */
    @Override
    public GatewayFilter apply(Object config) {
        return  (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION); // получение токена

            if (token == null || !token.startsWith("Bearer ")) { // Если токена нет или он не начинается корректно, то возвращается 401
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClient.get().uri("/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve() // Если /validate вернёт 4xx/5xx или произойдёт сетевое исключение, retrieve() сгенерирует ошибку
                    .toBodilessEntity() // Важен только статус
                    .then(chain.filter(exchange)); // При 2** управление передаётся основному фильтру и он уже идёт к целевому бэкенду
        };
    }

}
