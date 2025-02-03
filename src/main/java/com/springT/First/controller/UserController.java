package com.springT.First.controller;

import com.springT.First.entity.User;
import com.springT.First.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> users=userService.getAllUser();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }
    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user);
        return userService.verify(user);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAllUsers(){
        userService.deleteAllUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser){
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        userService.saveUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/userName/{userName}")
    public ResponseEntity<?> getUserById(@PathVariable String userName){
        Optional<User> user=userService.findByUserName(userName);
        if (user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User newUser){
        Optional<User> userDB=userService.findByUserName(newUser.getUserName());
        if (userDB.isPresent()){
            userService.deleteUserById(userDB.get().getId());
            userDB.get().setUserName(newUser.getUserName());
            userDB.get().setPassword(newUser.getPassword());

            userService.saveUser(userDB.get());
            return new ResponseEntity<>(userDB.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
