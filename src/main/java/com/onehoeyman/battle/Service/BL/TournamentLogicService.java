package com.onehoeyman.battle.Service.BL;

import com.onehoeyman.battle.Entity.Tournament;
import com.onehoeyman.battle.Service.Interface.ITournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentLogicService {

    @Autowired
    ITournamentService tournamentService;

    @Scheduled(fixedRate = 10000)
    public void startTournament(){
        List<Tournament> tournamentList = tournamentService.findAll();
        for (Tournament tournament :
                tournamentList) {

            System.out.println(tournament.getName() + "possede " + tournament.getNumberParticipants());
        }
    }
}
