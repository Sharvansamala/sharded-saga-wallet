package com.example.sharded_saga_wallet.services;

import com.example.sharded_saga_wallet.entities.User;
import com.example.sharded_saga_wallet.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
   
    public User createUser(User user) {
        log.info("Creating user: {}", user);
        User newUser = userRepository.save(user);
        log.info("User created with id: {} in database: {}", newUser.getId(), newUser.getId() % 2 + 1);
        return newUser;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findByName(String name){
        String pattern = "%"+name+"%";
//        return userRepository.searchByName(pattern);
        return null;
    }
    
}
