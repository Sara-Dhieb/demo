package com.myproject.BoardManagement.demo.controller;

import com.myproject.BoardManagement.demo.model.Action;
import com.myproject.BoardManagement.demo.model.User;
import com.myproject.BoardManagement.demo.repository.UserRepository;
import com.myproject.BoardManagement.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin("http://localhost:4200/")
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    //@GetMapping("/Hello")
//    public void Hello(){
//     System.out.println("Hello World");
//};
    @PostMapping("/admin/user")
//@RolesAllowed("admin")
    public ResponseEntity<User> saveEmployee(@RequestBody User user) {
        return ResponseEntity.ok(userServiceImpl.saveUser(user));
    }

    @GetMapping("/user/user")
    public ResponseEntity<List<User>> getUser() {

        return ResponseEntity.ok(userServiceImpl.findAllUsers());
    }

    @GetMapping("/user/{searchName}")
    public ResponseEntity<List<User>> getUsersDetails(@PathVariable String searchName) {
        return ResponseEntity.ok(userServiceImpl.getUsersByUsernameContaining(searchName));
    }
    @GetMapping("user/actions/{username}")
    public ResponseEntity<List<Action>> getUserActions(@PathVariable String username) {
        List<Action> actions = userServiceImpl.findUserActions(username);
        return ResponseEntity.ok(actions);
    }
    @GetMapping("/actionsBy/{username}/{date}")
    public ResponseEntity<List<Action>> getActionsByUsernameAndDate(
            @PathVariable("username") String username,
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Action> actions = userServiceImpl.getActionsByUsernameAndDate(username, date);
        return ResponseEntity.ok(actions);
    }


}
