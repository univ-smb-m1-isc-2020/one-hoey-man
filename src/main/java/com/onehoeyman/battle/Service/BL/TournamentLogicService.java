package com.onehoeyman.battle.Service.BL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onehoeyman.battle.Entity.Character;
import com.onehoeyman.battle.Entity.Fight;
import com.onehoeyman.battle.Entity.Status;
import com.onehoeyman.battle.Entity.Tournament;
import com.onehoeyman.battle.Service.Impl.CharacterService;
import com.onehoeyman.battle.Service.Impl.FightService;
import com.onehoeyman.battle.Service.Impl.TournamentService;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class TournamentLogicService {

    private TournamentService tournamentService;
    private CharacterService characterService;
    private FightService fightService;

    private final ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    public TournamentLogicService() {
    }

    @Autowired
    public TournamentLogicService(FightService fightService, CharacterService characterService, TournamentService tournamentService) {
        this.fightService = fightService;
        this.characterService = characterService;
        this.tournamentService = tournamentService;
    }

    public TournamentService getTournamentService() {
        return tournamentService;
    }

    public void setTournamentService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    public CharacterService getCharacterService() {
        return characterService;
    }

    public void setCharacterService(CharacterService characterService) {
        this.characterService = characterService;
    }

    public FightService getFightService() {
        return fightService;
    }

    public void setFightService(FightService fightService) {
        this.fightService = fightService;
    }

    @Scheduled(fixedRate = 5000)
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

    @Scheduled(fixedDelay = 10000)
    public void canStartTournament() throws Exception {
        System.out.println("Beginning ");
        List<Tournament> tournamentList = tournamentService.findAll();
        for (Tournament tournament :
                tournamentList) {
            if (tournament.getStatus() == Status.RoundFinished) {
                prepareNextRound(tournament);
                tournament.setStatus(Status.CanStart);
            } else if (tournament.getStatus() == Status.CanStart) {
                if (tournament.getRoundNumber() == 0) {
                    tournament.setStatus(Status.Finished);


//                    cleanFighters(tournament);
                } else {
                    tournament.setStatus(Status.InProgress);
                    //reloadFighter(tournament);
                    startFighting(tournament);
                    tournament.setStatus(Status.RoundFinished);
                }
            }
        }

    }


    private void cleanFighters(Tournament tournament) {
        System.out.println("Size " + tournament.getParticipants().size());
        /*for (Character character :
                tournament.getParticipants()) {
            character.setHead(null);
            character.setLeg(null);
            character.setChest(null);
            character.setHand(null);
            character.setTournament(null);

        }*/
    }

    private void prepareNextRound(Tournament tournament) {
        int borneSup = (int) Math.pow(2, tournament.getRoundNumber()) - 1;
        int borneInf = (int) Math.pow(2, tournament.getRoundNumber() - 1);
        Hibernate.initialize(tournament.getFights());
        for (Fight fight :
                tournament.getFights()) {

            if (fight.getNumber() >= borneInf && fight.getNumber() <= borneSup) {
                if (fight.getNumber() % 2 == 0) {
                    int nextRoundNumber = fight.getNumber() / 2;
                    Fight nextFight = fightService.findByTournamentAndNumber(tournament, nextRoundNumber);
                    System.out.println("Prepare fight number " + fight.getNumber());

                    Character winner = fight.getWinner();
                    winner.setFight1(null);
                    winner.setFight2(null);
                    nextFight.setFighter1(winner);
                } else {
                    int nextRoundNumber = (fight.getNumber() - 1) / 2;
                    Fight nextFight = fightService.findByTournamentAndNumber(tournament, nextRoundNumber);
                    Character winner = fight.getWinner();
                    winner.setFight1(null);
                    winner.setFight2(null);
                    nextFight.setFighter2(fight.getWinner());
                }
            }
        }
        tournament.setRoundNumber(tournament.getRoundNumber() - 1);
    }

    private void startFighting(Tournament tournament) throws Exception {
        int borneSup = (int) Math.pow(2, tournament.getRoundNumber()) - 1;
        int borneInf = (int) Math.pow(2, tournament.getRoundNumber() - 1);
        Hibernate.initialize(tournament.getFights());
        for (Fight fight :
                tournament.getFights()) {
            if (fight.getNumber() >= borneInf && fight.getNumber() <= borneSup) {
                startFight(fight);
            }
        }
    }

    public void startFight(Fight fight) throws Exception {

        Combat combat;
        Character winner;

        ArrayList<String> messages = new ArrayList<String>();
        Random rand = new Random();


        Hibernate.initialize(fight.getFighter1());
        Hibernate.initialize(fight.getFighter2());

        System.out.println("Fight number " + fight.getNumber());
        Character fighter1 = fight.getFighter1();
        Character fighter2 = fight.getFighter2();
        int damageFighter1 = 0;
        int damageFighter2 = 0;
        int turn = fighter1.getTotalAgility() > fighter2.getTotalAgility() ? 0 : 1;

        messages.add((turn % 2 == 0 ? fighter1.getName() : fighter2.getName())
               + " a l'avantage, à lui de commencer");

        int jetAttaque;
        int jetCritique;
        int multiplicateurDegat = 1;
        int tirage;
        double coefficientDegat = 0.4;

        while (!((fighter1.getTotalHp() - damageFighter1 < 0) ||
                (fighter2.getTotalHp() - damageFighter2 < 0))) {

            tirage = rand.nextInt(100) + 1;
            multiplicateurDegat = 1;
            jetAttaque = rand.nextInt(100) + 1;

            if (turn % 2 == 0) {
                // Joueur 1 attaque
                if (jetAttaque > 2 * fighter2.getTotalAgility()) {

                    messages.add(fighter1.getName() + " va attaquer !");

                    jetCritique = rand.nextInt(100) + 1;
                    if (jetCritique == 100) {
                        messages.add("Les dieux accompagnent la frappe de " + fighter1.getName());
                        multiplicateurDegat = 3;
                    } else if (jetCritique > 100 - fighter1.getTotalIntelligence() * 1.5) {
                        messages.add("Ouch ! Critical hit");
                        multiplicateurDegat = 2;
                    }
                    int degat = (rand.nextInt((int) (fighter1.getTotalStrength() * coefficientDegat)) + 1) * multiplicateurDegat;
                    damageFighter2 += degat;
                    messages.add(fighter1.getName() + " a infligé " + degat + " !");
                } else {
                    messages.add("Oh no ! " + fighter1.getName() + " a manqué son coup !");
                }

            } else {
                // Joueur 2 attaque
                if (jetAttaque > 2 * fighter1.getTotalAgility()) {

                    messages.add(fighter2.getName() + " va attaquer !");

                    jetCritique = rand.nextInt(100) + 1;
                    if (jetCritique == 100) {
                        messages.add("Les dieux accompagnent la frappe de " + fighter2.getName());
                        multiplicateurDegat = 3;
                    } else if (jetCritique > 100 - fighter2.getTotalIntelligence() * 1.5) {
                        messages.add("Ouch ! Critical hit");
                        multiplicateurDegat = 2;
                    }
                    int degat = (rand.nextInt((int) (fighter2.getTotalStrength() * coefficientDegat)) + 1) * multiplicateurDegat;
                    damageFighter1 += degat;
                    messages.add(fighter2.getName() + " a infligé " + degat + " !");
                } else {
                    messages.add("Oh no ! " + fighter2.getName() + " a manqué son coup !");
                }
            }

            turn += 1;
        }

        if (fighter1.getTotalHp() - damageFighter1 < 0) {
            fighter2.getInventory().addAll(fighter1.getInventory());

            fighter2.setNumberVictory(fighter2.getNumberVictory() + 1);
            if (fight.getNumber() == 1) {
                fighter2.setTournamentVictory(fighter2.getTournamentVictory() + 1);
            }
            fight.setWinner(fighter2);

            messages.add(fighter2.getName() + " a gagné contre " + fighter1.getName());

            winner = fighter2;


        } else {
            fighter1.getInventory().addAll(fighter2.getInventory());

            fighter1.setNumberVictory(fighter1.getNumberVictory() + 1);
            if (fight.getNumber() == 1) {
                fighter1.setTournamentVictory(fighter1.getTournamentVictory() + 1);
            }

            fight.setWinner(fighter1);

            messages.add(fighter1.getName() + " a gagné contre " + fighter2.getName());

            winner = fighter1;


        }

        combat = new Combat(winner,messages);


        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(combat));
        String combatString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(combat);

        String filename = "resume_" + fight.getTournoi().getId() + "_" + fight.getId();
        Files.write(Paths.get("./log/" + filename), combatString.getBytes());

    }

}
