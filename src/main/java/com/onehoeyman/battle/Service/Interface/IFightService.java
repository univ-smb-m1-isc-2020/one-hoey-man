package com.onehoeyman.battle.Service.Interface;

import com.onehoeyman.battle.Entity.Fight;

import java.util.List;

public interface IFightService {

    public List<Fight> findAll();

    public Fight findById(Long id);
}
