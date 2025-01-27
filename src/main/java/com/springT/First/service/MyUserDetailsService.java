package com.springT.First.service;

import com.springT.First.entity.User;
import com.springT.First.entity.UserPrincipal;
import com.springT.First.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUserName(username);
        if (user.isEmpty()){
            System.out.println("User not found!");
            throw new UsernameNotFoundException("User not found!");
        }
        return new UserPrincipal(user.get());
    }
}
