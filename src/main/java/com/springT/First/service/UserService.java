package com.springT.First.service;

import com.springT.First.entity.JournalEntry;
import com.springT.First.entity.User;
import com.springT.First.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    public void saveUser(User user){
        userRepository.save(user);
    }
    public String verify(User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (authentication.isAuthenticated()){
            return "Success";
        }
        return "Failure";
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public void deleteAllUser(){
        userRepository.deleteAll();
    }
    public Optional<User> getUserById(ObjectId Id){
        return userRepository.findById(Id);
    }
    public void deleteUserById(ObjectId Id){
        userRepository.deleteById(Id);
    }
    public void updateUserById(ObjectId Id, User user){
        userRepository.save(user);
    }
    public Optional<User> findByUserName(String user){
        return userRepository.findByUserName(user);
    }
}
