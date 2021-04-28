package com.onehoeyman.battle.Api;

import com.onehoeyman.battle.Controller.Api.CharacterController;
import com.onehoeyman.battle.Entity.User;
import com.onehoeyman.battle.Service.Interface.ICharacterService;
import com.onehoeyman.battle.Service.Interface.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class CharacterControllerTest {
    private ICharacterService characterService;
    private IUserService userService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        this.characterService = mock(ICharacterService.class);
        this.userService = mock(IUserService.class);
        this.mockMvc = standaloneSetup(new CharacterController(characterService,userService)).build();
    }

//    @Test
    public void testingLogin() throws Exception {
        when(userService.login(anyString(), anyString()))
                .thenReturn(Optional.of(new User(1,"nepnep@nepnep.com", "nepnep", "nepnep")));

        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"nepnep@nepnep.com\", \"characters\":[] , \"password\": \"nepnep\", \"id\":}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\": \"titi\", \"email\": \"titi@gmail.com\"}"));
    }
}
