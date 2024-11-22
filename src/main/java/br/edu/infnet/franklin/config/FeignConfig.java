package br.edu.infnet.franklin.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {

			@Value("${cosmos.api.token}")
			private String token;
			
			@Override
            public void apply(RequestTemplate template) {
                template.header("Content-Type", "application/json");
                template.header("X-Cosmos-Token", token); 
                template.header("User-Agent", "Cosmos-API-Request");
            }
        };
    }
}
