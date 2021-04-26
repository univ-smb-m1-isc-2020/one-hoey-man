package com.onehoeyman.battle.Service.Interface;

import com.onehoeyman.battle.Entity.Fight;
import com.onehoeyman.battle.Entity.Tournament;

import java.util.List;
import java.util.Optional;

public interface IFightService {

    public List<Fight> findAll();

    public Optional<Fight> findById(Long id);

    public Fight save(Fight fight);

    public Fight findByTournamentAndNumber(Tournament tournament, int number);
}
