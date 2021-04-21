package com.onehoeyman.battle.Service.BL;

import com.google.gson.JsonArray;
import com.onehoeyman.battle.Entity.Character;
import com.onehoeyman.battle.Entity.Fight;
import com.onehoeyman.battle.Entity.Status;
import com.onehoeyman.battle.Entity.Tournament;
import com.onehoeyman.battle.Service.Interface.ICharacterService;
import com.onehoeyman.battle.Service.Interface.ITournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TournamentLogicService {

    @Autowired
    ITournamentService tournamentService;
    @Autowired
    ICharacterService characterService;

    @Scheduled(fixedRate = 10000)
    public void startTournament() {
        List<Tournament> tournamentList = tournamentService.findAll();
        for (Tournament tournament :
                tournamentList) {
            if (tournament.getStatus() == Status.NotStarted && tournament.getNumberParticipants() == tournament.getMaxSize()) {
                tournament.setStatus(Status.CanStart);
                System.out.println(tournament.getName() + " can start");
                tournamentService.save(tournament);
            }
        }
    }

    @Scheduled(fixedDelay = 20000)
    public void canStartTournament() throws InterruptedException {
        System.out.println("Beginning ");
        List<Tournament> tournamentList = tournamentService.findAll();
        for (Tournament tournament :
                tournamentList) {
            if (tournament.getStatus() == Status.CanStart) {
                if (tournament.getRoundNumber() == 0) {
                    tournament.setStatus(Status.Finished);
                } else {
                    tournament.setStatus(Status.InProgress);
                    startFighting(tournament);
                    prepareNextRound(tournament);
                    tournament.setStatus(Status.RoundFinished);
                }
            }
        }
        System.out.println("Before ");
        Thread.sleep(10000);
        System.out.println("After");


    }

    public void startFighting(Tournament tournament) {
        int borneSup = (int) Math.pow(2, tournament.getRoundNumber()) - 1;
        int borneInf = (int) Math.pow(2, tournament.getRoundNumber() - 1);
        for (Fight fight :
                tournament.getFights()) {
            if (fight.getNumber() >= borneInf || fight.getNumber() <= borneSup) {
                startFight(fight);
            }
        }
    }

    public void startFight(Fight fight) {
        JSONObject json = new JSONObject();
        JsonArray messages = new JsonArray();

        Random rand = new Random();

        Character fighter1 = fight.getFighter1();
        Character fighter2 = fight.getFighter2();
        int damageFighter1 = 0;
        int damageFighter2 = 0;
        int turn = fighter1.getTotalAgility() > fighter2.getTotalAgility() ? 0 : 1;

        messages.add((turn % 2 == 0 ? fighter1.getName() : fighter2.getName())
                + " a l'avantage, à lui de commencer");

        while (!((fighter1.getTotalHp() - damageFighter1 < 0) ||
                (fighter2.getTotalHp() - damageFighter2 < 0))) {
            int tirage = rand.nextInt(100) + 1;
            if (turn % 2 == 0) {
                // Joueur 1 attaque
                    
            } else {
                // Joueur 2 attaque

            }
        }

    }

}
