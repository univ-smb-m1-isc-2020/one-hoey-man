package com.onehoeyman.battle.Service.Impl;

import com.onehoeyman.battle.Entity.User;
import com.onehoeyman.battle.Repository.UserRepository;
import com.onehoeyman.battle.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User register(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> login(String username, String password) {
        return repository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername() {
        return null;
    }

    @Override
    public User findById(int id) {
        Optional<User> us = repository.findById(new Long(id));
        return us.get();
    }
}
