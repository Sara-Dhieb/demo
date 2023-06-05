package com.myproject.BoardManagement.demo.controller;

import com.myproject.BoardManagement.demo.service.keycloak.KeycloakUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zied.bagga
 * @since 05/06/2023
 */

@RestController
@RequestMapping("/public")
public class LoginController {

    private final KeycloakUserService keycloakUserService;

    public LoginController(KeycloakUserService keycloakUserService) {
        this.keycloakUserService = keycloakUserService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        String token = keycloakUserService.authenticate(username, password);
        return token;
    }
}
