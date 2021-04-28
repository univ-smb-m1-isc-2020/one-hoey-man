package com.onehoeyman.battle.Controller.Api;

import com.onehoeyman.battle.Entity.Character;
import com.onehoeyman.battle.Service.Interface.ICharacterService;
import com.onehoeyman.battle.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/characters")
public class CharacterController {

    ICharacterService characterService;
    IUserService userService;

    public CharacterController(ICharacterService characterService, IUserService userService) {
        this.characterService = characterService;
        this.userService = userService;
    }

    /**
     * All characters
     * One character returned  : {
     *         "id": 2,
     *         "inventory": [],
     *         "name": "Test2",
     *         "hp": 10,
     *         "strength": 10,
     *         "intelligence": 10,
     *         "agility": 10,
     *         "numberVictory": 0,
     *         "tournamentVictory": 0,
     *         "head": null,
     *         "chest": null,
     *         "leg": null,
     *         "hand": null,
     *         "totalAgility": 10,
     *         "totalHp": 10,
     *         "totalIntelligence": 10,
     *         "totalStrength": 10
     * }
     * @return Character[]
     */
    @GetMapping(path = "")
    @ResponseBody
    public List<Character> findCharacters() {
        List<Character> characters = (List<Character>) characterService.findAll();
        return characters;
    }

    /**
     * Get all characters of a user
     * @param creator_id : Creator's id
     * @return Character[]
     */
    @GetMapping(path = "creator/{creator_id}")
    @ResponseBody
    public List<Character> findCharactersByCreatorId(@PathVariable int creator_id) {
        List<Character> characters = (List<Character>) characterService.findByCreatorId(creator_id);
        return characters;
    }

    /**
     * Create a character for the user passed by id
     * @param character : Character object
     * @param creator_id : The creator's id
     * @return Character
     */
    @PostMapping(path = "create", produces = "application/json")
    @ResponseBody
    public Character create(@RequestBody @Validated Character character, @RequestParam("creator_id") int creator_id) {
        character.setCreator(userService.findById(creator_id));
        return characterService.save(character);
    }


}
