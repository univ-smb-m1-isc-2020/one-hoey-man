package com.onehoeyman.battle.Service.Interface;

import com.onehoeyman.battle.Entity.Fight;

import java.util.List;
import java.util.Optional;

public interface IFightService {

    public List<Fight> findAll();

    public Optional<Fight> findById(Long id);

    public Fight save(Fight fight);
}
