package com.myproject.BoardManagement.demo.controller;

import com.myproject.BoardManagement.demo.model.User;
import com.myproject.BoardManagement.demo.service.keycloak.KeyCloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/user")
public class KeyCloakController {
    @Autowired
    KeyCloakService service;

    @PostMapping
    public String addUser(@RequestBody User user){
        service.createUser();
        return "User Added Successfully.";
    }

    @GetMapping(path = "/{userName}")
    public List<UserRepresentation> getUser(@PathVariable("userName") String userName){
        List<UserRepresentation> user = service.getUser(userName);
        return user;
    }

    @PutMapping(path = "/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @RequestBody User user){
        service.updateUser(userId, user);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping(path = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        service.deleteUser(userId);
        return "User Deleted Successfully.";
    }

    @GetMapping(path = "/verification-link/{userId}")
    public String sendVerificationLink(@PathVariable("userId") String userId){
        service.sendVerificationLink(userId);
        return "Verification Link Send to Registered E-mail Id.";
    }

    @GetMapping(path = "/reset-password/{userId}")
    public String sendResetPassword(@PathVariable("userId") String userId){
        service.sendResetPassword(userId);
        return "Reset Password Link Send Successfully to Registered E-mail Id.";
    }
}
