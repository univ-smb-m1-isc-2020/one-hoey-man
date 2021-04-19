package com.onehoeyman.battle.Service.Interface;

import com.onehoeyman.battle.Entity.Character;
import com.onehoeyman.battle.Entity.Tournament;

import java.util.List;
import java.util.Optional;

public interface ITournamentService {

    public List<Tournament> findAll();

    public Optional<Tournament> findById(Long id);

    public Tournament save(Tournament tournament);
}
