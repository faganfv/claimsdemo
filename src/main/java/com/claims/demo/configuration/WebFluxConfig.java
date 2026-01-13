package com.claims.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.multipart.DefaultPartHttpMessageReader;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

    @Bean
    public DefaultPartHttpMessageReader partHttpMessageReader() {
        DefaultPartHttpMessageReader reader = new DefaultPartHttpMessageReader();
        reader.setMaxDiskUsagePerPart(50 * 1024 * 1024); // 50MB per part
        return reader;
    }
}