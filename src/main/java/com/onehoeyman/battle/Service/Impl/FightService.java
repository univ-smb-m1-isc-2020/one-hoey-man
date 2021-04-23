package com.onehoeyman.battle.Service.Impl;

import com.onehoeyman.battle.Entity.Fight;
import com.onehoeyman.battle.Entity.Tournament;
import com.onehoeyman.battle.Repository.FightRepository;
import com.onehoeyman.battle.Service.Interface.IFightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FightService implements IFightService {
    @Autowired
    private FightRepository repository;

    @Override
    public List<Fight> findAll() {
        return (List<Fight>) repository.findAll();
    }

    @Override
    public Optional<Fight> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Fight save(Fight fight) {
        return repository.save(fight);
    }

    public Fight findByTournamentAndNumber(Tournament tournament, int number) {
        return repository.findByTournoiAndNumber(tournament, number);
    }
}
