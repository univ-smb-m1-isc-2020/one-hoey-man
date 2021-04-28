package com.onehoeyman.battle.Api;

import com.onehoeyman.battle.Controller.Api.CharacterController;
import com.onehoeyman.battle.Controller.Api.UserController;
import com.onehoeyman.battle.Entity.User;
import com.onehoeyman.battle.Service.Impl.CharacterService;
import com.onehoeyman.battle.Service.Impl.UserService;
import com.onehoeyman.battle.Service.Interface.ICharacterService;
import com.onehoeyman.battle.Service.Interface.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private IUserService userService;
    @Autowired
    private MockMvc mockMvc;




    public void testingLogin() throws Exception {
        when(userService.login(anyString(), anyString()))
                .thenReturn(Optional.of(new User(1,"nepnep@nepnep.com", "nepnep", "nepnep")));

        mockMvc.perform(get("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"nepnep\" , \"password\": \"nepnep\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"email\": \"nepnep@nepnep.com\", \"characters\":[] , \"password\": \"nepnep\", \"username\":nepnep, \"id\":1}"));
    }
}
