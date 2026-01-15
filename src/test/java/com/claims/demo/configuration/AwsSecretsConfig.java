package com.claims.demo.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Configuration
@EnableConfigurationProperties(AwsSecretsProperties.class)
public class AwsSecretsConfig {

    @Bean
    @ConditionalOnProperty(
            prefix = "aws.secrets",
            name = "enabled",
            havingValue = "true",
            matchIfMissing = true
    )
    public SecretsManagerClient secretsManagerClient() {
        return SecretsManagerClient.builder().build();
    }
}