package com.onehoeyman.battle.Service.Impl;

import com.onehoeyman.battle.Entity.Character;
import com.onehoeyman.battle.Entity.Tournament;
import com.onehoeyman.battle.Repository.TournamentRepository;
import com.onehoeyman.battle.Service.Interface.ITournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService  implements ITournamentService {

    @Autowired
    private TournamentRepository repository;

    @Override
    public List<Tournament> findAll() {
        List<Tournament> tournaments = (List<Tournament>) repository.findAll();
        return tournaments;
    }

    @Override
    public Optional<Tournament> findById(Long id) {
        Optional<Tournament> tournament = repository.findById(id);
        return tournament;
    }

    @Override
    public Tournament save(Tournament tournament) {
        return repository.save(tournament);
    }

    @Override
    public void delete(Tournament tournament) {
        for (Character character:
             tournament.getParticipants()) {
            character.setTournament(null);
        }
        //repository.delete(tournament);
    }
}
