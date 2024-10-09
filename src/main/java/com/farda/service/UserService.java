package com.farda.service;

import com.farda.model.User;
import com.farda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(final Long id){
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(final Long id){
        userRepository.deleteById(id);
    }

    public User saveUser(User user){
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
