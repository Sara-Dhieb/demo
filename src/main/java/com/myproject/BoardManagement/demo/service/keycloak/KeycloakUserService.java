package com.myproject.BoardManagement.demo.service.keycloak;

/**
 * @author zied.bagga
 * @since 03/06/2023
 */

import com.myproject.BoardManagement.demo.dto.UserDto;
import org.apache.catalina.Realm;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class KeycloakUserService {


    private static final String KEYCLOAK_SERVER_URL = "http://localhost:8888/auth";
    private static final String REALM = "testProject";
    private static final String CLIENT_ID = "spring-test";
    private static final String CLIENT_SECRET = "j5yJE6ckEqvorKzMMFTHooIWmaJO49kl";


    private final Keycloak keycloak;

    @Autowired
    public KeycloakUserService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public void createUser(String username, String email, String password) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setEnabled(true);

        keycloak.realm("testProject").users().create(user);
    }

    public void createUser(UserDto userDto) {

        UserRepresentation user = new UserRepresentation();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setEnabled(true);
        UsersResource usersResource = keycloak.realm("testProject").users();

        try (Response result = usersResource.create(user)) {

            if (result.getStatus() == 201) {

                List<String> paths = Arrays.asList(result.getLocation().getPath().split("/"));
                String userId = paths.get(paths.size() - 1);
                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setTemporary(false);
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(userDto.getPassword());
                // Set password credential
                usersResource.get(userId).resetPassword(passwordCred);

                // Role Assignment
                RoleRepresentation userRole = keycloak.realm("testProject").roles().get("user").toRepresentation();
                usersResource.get(userId).roles().realmLevel().add(Collections.singletonList(userRole));

            } else {
                if (result.getStatus() == 409) {
                }
            }
        }
    }

    public String authenticate(String username, String password) {
        KeycloakBuilder builder = KeycloakBuilder.builder()
                .serverUrl(KEYCLOAK_SERVER_URL)
                .realm(REALM)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .username(username)
                .password(password);

        AccessTokenResponse response = builder.build().tokenManager().getAccessToken();
        return response.getToken();
    }

}


