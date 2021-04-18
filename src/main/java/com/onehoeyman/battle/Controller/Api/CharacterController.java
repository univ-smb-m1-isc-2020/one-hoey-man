package com.onehoeyman.battle.Controller.Api;

import com.onehoeyman.battle.Entity.Character;
import com.onehoeyman.battle.Service.Interface.ICharacterService;
import com.onehoeyman.battle.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/characters")
public class CharacterController {

    @Autowired
    ICharacterService characterService;
    @Autowired
    IUserService userService;
    @GetMapping(path = "")
    public List<Character> findCharacters() {
        List<Character> characters = (List<Character>) characterService.findAll();
        return characters;
    }

    @PostMapping(path = "create", produces = "application/json")
    public Character create(@RequestBody @Validated Character character, @RequestParam("creator_id") int id) {
        character.setCreator(userService.findById(id));
        return characterService.save(character);
    }


}
