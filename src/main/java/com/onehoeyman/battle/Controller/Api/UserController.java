package com.onehoeyman.battle.Controller.Api;

import com.onehoeyman.battle.Entity.User;
import com.onehoeyman.battle.Form.LoginForm;
import com.onehoeyman.battle.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("api/users/")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * Login with password and username
     * @param form {"username": , "password":}
     * @return User
     */
    @GetMapping(path = "login")
    @ResponseBody
    public Optional<User> login(@RequestBody LoginForm form){
        return userService.login(form.getUsername(), form.getPassword());
    }

    /**
     * Register user
     *
     * @param {"username": , "password":, "email"}
     * @return
     */
    @PostMapping(path = "register", consumes = "application/json")
    @ResponseBody
    public User register(@RequestBody User user){
        if(userService.existsUserByUsername(user.getUsername())){
            return null;
        }
        return userService.register(user);
    }
}
