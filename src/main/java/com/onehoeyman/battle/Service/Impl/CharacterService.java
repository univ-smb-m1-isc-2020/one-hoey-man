package com.onehoeyman.battle.Service.Impl;

import com.onehoeyman.battle.Entity.Character;
import com.onehoeyman.battle.Repository.CharacterRepository;
import com.onehoeyman.battle.Service.Interface.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService implements ICharacterService {

    @Autowired
    private CharacterRepository repository;

    @Override
    public List<Character> findAll() {
        List<Character> characters = (List<Character>) repository.findAll();
        return characters;
    }

    @Override
    public Optional<Character> findById(Long id) {
        Optional<Character> character = repository.findById(id);
        return character;
    }

    @Override
    public Character save(Character character) {
        return repository.save(character);
    }
}
