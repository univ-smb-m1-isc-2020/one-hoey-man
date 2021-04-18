package com.onehoeyman.battle.Service.Interface;

import com.onehoeyman.battle.Entity.User;

import java.util.Optional;

public interface IUserService {

    public User register(User user);

    public Optional<User> login(String username, String password);

    public boolean existsUserByUsername(String username) ;

    User findById(int id);
}

