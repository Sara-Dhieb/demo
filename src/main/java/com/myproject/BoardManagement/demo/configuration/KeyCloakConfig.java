package com.myproject.BoardManagement.demo.configuration;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeyCloakConfig {
    @Bean
    public Keycloak keycloakClient() {
        return Keycloak.getInstance(
                "http://localhost:8080/auth",
                "master",
                "admin",
                "admin",
                "admin-cli");
    }

    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
}
