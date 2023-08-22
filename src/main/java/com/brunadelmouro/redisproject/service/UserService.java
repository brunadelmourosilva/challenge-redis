package com.brunadelmouro.redisproject.service;

import com.brunadelmouro.redisproject.model.User;
import com.brunadelmouro.redisproject.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CacheEvict(value = "saveUser", allEntries = true)
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Cacheable(value = "userById", key = "#id")
    public User getUserById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found."));
    }
}
