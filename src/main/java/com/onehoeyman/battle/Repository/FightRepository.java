package com.onehoeyman.battle.Repository;

import com.onehoeyman.battle.Entity.Fight;
import com.onehoeyman.battle.Entity.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FightRepository extends CrudRepository<Fight, Long> {

    public Fight findByTournamentAndNumber(Tournament tournament, int number);
}
