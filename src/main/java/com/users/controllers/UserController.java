package com.users.controllers;

import com.users.dto.NewUserDTO;
import com.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("get-user/{userId}")
    public ResponseEntity getUserById(@PathVariable Long userId) {
        return userService.getUsersById(userId);
    }

    @PostMapping("new-user")
    public ResponseEntity createNewUser(@RequestBody NewUserDTO request) {
        return userService.createUser(request);
    }

    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }
}
