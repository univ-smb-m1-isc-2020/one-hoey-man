package com.onehoeyman.battle.Controller.Api;

import com.onehoeyman.battle.Entity.Character;
import com.onehoeyman.battle.Entity.Fight;
import com.onehoeyman.battle.Entity.Tournament;
import com.onehoeyman.battle.Service.Interface.ICharacterService;
import com.onehoeyman.battle.Service.Interface.ITournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tournaments/")
public class TournamentController {
    @Autowired
    ITournamentService tournamentService;
    @Autowired
    ICharacterService characterService;
    @Autowired

    /**
     * All tournaments
     *
     * @return Tournament[]
     */
    @GetMapping(path = "")
    public List<Tournament> findTournaments() {
        return (List<Tournament>) tournamentService.findAll();
    }

    /**
     * Create a tournament
     *
     * @param tournament : Tournament
     * @return Tournament
     */
    @PostMapping(path = "create", produces = "application/json")
    public Tournament create(@RequestBody Tournament tournament) {
        Tournament tournament_created = tournamentService.save(tournament);
        for (int i = 1; i <= tournament_created.getMaxSize(); i++) {
            Fight fight = new Fight(i, tournament_created);
        }
        return tournament_created;
    }

    /**
     * Register the character to the tournament
     *
     * @param tournament_id : Tournament's id
     * @param character_id  : Character's id
     * @return
     */
    @PostMapping(path = "{tournament_id}/register/{character_id}", produces = "application/json")
    public Tournament register(@PathVariable int tournament_id, @PathVariable int character_id) {
        Optional<Tournament> tournament = tournamentService.findById(new Long(tournament_id));
        Optional<Character> character = characterService.findById(new Long(character_id));
        Tournament tournament1 = tournament.get();
        tournament1.registerParticipants(character.get());
        return tournamentService.save(tournament1);
    }
}
