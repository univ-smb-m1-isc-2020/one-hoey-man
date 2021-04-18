package com.onehoeyman.battle.Service.Interface;

import com.onehoeyman.battle.Entity.Tournament;

import java.util.List;

public interface ITournamentService {

    public List<Tournament> findAll();

    public Tournament findById(Long id);
}
