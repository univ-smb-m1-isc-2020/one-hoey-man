package com.onehoeyman.battle.Service.Interface;

import com.onehoeyman.battle.Entity.Character;
import com.onehoeyman.battle.Entity.Tournament;

import java.util.List;
import java.util.Optional;

public interface ICharacterService {

    public List<Character> findAll();

    public Optional<Character> findById(Long id);

    public Character save(Character character);

    public List<Character> findByCreatorId(int creator_id);

    public List<Character> findCharactersByTournament(Tournament tournament);
}
