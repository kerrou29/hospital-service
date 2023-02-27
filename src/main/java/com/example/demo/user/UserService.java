/*
package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List <User> users = userRepository.findAll();
        System.out.println("Getting data from DB: " + users);
        return users;
    }

    public void addUser(User user) {
        Optional<User> userOptional = userRepository.findUserByUsername(user.getUsername());
        if(userOptional.isPresent()){
            throw new IllegalStateException("Username taken!");
        }
        userRepository.save(user);
    }

    public void signIn(User user) {
        Optional<User> usernameOptional = userRepository.findUserByUsername(user.getUsername());
        Optional<User> passwordOptional = userRepository.findUserByUsername(user.getPassword());

        if(usernameOptional.isPresent() && passwordOptional.isPresent()){
            System.out.println("Authentication successful!");
        }else{
            throw new IllegalStateException("Incorrect Username and Password!");
        }
    }
}
*/
