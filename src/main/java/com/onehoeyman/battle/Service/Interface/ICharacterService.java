package com.onehoeyman.battle.Service.Interface;

import com.onehoeyman.battle.Entity.Character;

import java.util.List;
import java.util.Optional;

public interface ICharacterService {

    public List<Character> findAll();

    public Optional<Character> findById(Long id);

    public Character save(Character character);
}
