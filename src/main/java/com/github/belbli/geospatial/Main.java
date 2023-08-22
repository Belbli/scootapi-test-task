package com.github.belbli.geospatial;

import com.github.belbli.requestlimiter.resolver.KeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    @Bean
    public KeyResolver keyResolver() {
        return request -> request.getHeader("X-API-KEY");
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}