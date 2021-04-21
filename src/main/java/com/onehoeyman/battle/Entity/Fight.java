package com.onehoeyman.battle.Entity;

import com.fasterxml.jackson.annotation.*;
import com.onehoeyman.battle.Service.Interface.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "fight")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Fight {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number")
    private int number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fighter_id_1", referencedColumnName = "id", nullable = true)
    private Character fighter1;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fighter_id_2", referencedColumnName = "id", nullable = true)
    private Character fighter2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id", referencedColumnName = "id", nullable = true)
    private Character winner;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    @JsonIgnore
    private Tournament tournament;

    public Fight() {
    }

    public Fight(int number, Tournament tournament) {
        this.number = number;
        this.tournament = tournament;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Character getFighter1() {
        return fighter1;
    }

    public void setFighter1(Character fighter1) {
        this.fighter1 = fighter1;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Character getFighter2() {
        return fighter2;
    }

    public void setFighter2(Character fighter2) {
        this.fighter2 = fighter2;
    }

    public Character getWinner() {
        return winner;
    }

    public void setWinner(Character winner) {
        this.winner = winner;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public void startFight() {

    }
}
