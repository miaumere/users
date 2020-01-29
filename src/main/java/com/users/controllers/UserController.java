package com.users.controllers;

import com.users.requests.EditUserRequest;
import com.users.requests.NewUserRequest;
import com.users.requests.SearchUserArgsRequest;
import com.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("get-user/{userId}")
    public ResponseEntity getUserById(@PathVariable Long userId) {
        return userService.getUsersById(userId);
    }

    @GetMapping("search-user")
    public ResponseEntity searchUser(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> surname,
            @RequestParam Optional<Integer> grade,
            @RequestParam Optional<Integer> salary) {
        return userService.searchUser(name, surname, grade, salary);
    }

    @PostMapping("new-user")
    public ResponseEntity createNewUser(@RequestBody NewUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("edit-user")
    public ResponseEntity editUser(@RequestBody EditUserRequest request) {
        return userService.editUser(request);
    }

    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }
}
