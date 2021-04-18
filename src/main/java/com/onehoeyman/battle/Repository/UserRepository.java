package com.onehoeyman.battle.Repository;

import com.onehoeyman.battle.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("Select u FROM User u WHERE u.username = :username and u.password = :password")
    public Optional<User> findUserByUsernameAndPassword(String username, String password);

    boolean existsUserByUsername(String username);
}
