package com.myproject.BoardManagement.demo.configuration;

import org.keycloak.OAuth2Constants;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeyCloakConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "https://localhost:8080/auth/";
    public final static String realm = "javapuzzle";
    final static String clientId = "java-puzzle-client";
    final static String userName = "admin_user";
    final static String password = "admin";


    public KeyCloakConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)


                    .build();
        }


        return keycloak;
    }

    public UsersResource getRealm() {
        return getInstance().realm(realm).users();
    }
}
