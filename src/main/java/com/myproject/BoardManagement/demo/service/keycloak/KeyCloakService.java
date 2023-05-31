package com.myproject.BoardManagement.demo.service.keycloak;

import com.myproject.BoardManagement.demo.Credentials;
import com.myproject.BoardManagement.demo.configuration.KeyCloakConfig;
import com.myproject.BoardManagement.demo.model.User;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


    @AllArgsConstructor
    @Service
    public class KeyCloakService {
        @Autowired
        KeyCloakConfig keyCloakConfig;

        //        public void addUser(User user){
//            CredentialRepresentation credential = Credentials
//                    .createPasswordCredentials(user.getPassword());
//            UserRepresentation userDTO = new UserRepresentation();
//            userDTO.setUsername(user.getUsername());
//            userDTO.setFirstName(user.getFirstname());
//            userDTO.setLastName(user.getLastName());
//            userDTO.setEmail(user.getEmail());
//            userDTO.setCredentials(Collections.singletonList(credential));
//            userDTO.setEnabled(true);
//
//            UsersResource instance = getInstance();
//            instance.create(userDTO);
//        }
        public void createUser() {
            UserRepresentation userRepresentation = new UserRepresentation();

            userRepresentation.setUsername("username");
            userRepresentation.setFirstName("first-name");
            userRepresentation.setLastName("last-name");
            userRepresentation.setEmail("test@email.com");

            Response response = keyCloakConfig.getRealm().create(userRepresentation);
            //If user is created successfully 200 is returned for response status.

            //Set password flow
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            String userId = CreatedResponseUtil.getCreatedId(response);
            passwordCred.setTemporary(false);
            passwordCred.setType("password");
            passwordCred.setValue("some-password");
            UserResource userResource = keyCloakConfig.getRealm().get(userId);
            userResource.resetPassword(passwordCred);
        }
        public List<UserRepresentation> getUser(String userName){
            UsersResource usersResource = getInstance();
            List<UserRepresentation> user = usersResource.search(userName, true);
            return user;

        }

        public void updateUser(String userId, User user){
            CredentialRepresentation credential = Credentials
                    .createPasswordCredentials(user.getPassword());
            UserRepresentation userDTO = new UserRepresentation();
            userDTO.setUsername(user.getUsername());
            userDTO.setFirstName(user.getFirstname());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
            userDTO.setCredentials(Collections.singletonList(credential));
            UsersResource usersResource = getInstance();
            usersResource.get(userId).update(userDTO);
        }
        public void deleteUser(String userId){
            UsersResource usersResource = getInstance();
            usersResource.get(userId)
                    .remove();
        }


        public void sendVerificationLink(String userId){
            UsersResource usersResource = getInstance();
            usersResource.get(userId)
                    .sendVerifyEmail();
        }

        public void sendResetPassword(String userId){
            UsersResource usersResource = getInstance();

            usersResource.get(userId)
                    .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
        }

        public UsersResource getInstance(){
            return KeyCloakConfig.getInstance().realm(KeyCloakConfig.realm).users();
        }


    }

