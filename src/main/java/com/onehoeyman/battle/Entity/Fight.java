package com.onehoeyman.battle.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "fight")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Fight {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number")
    private int number;

    @OneToOne
    @JoinColumn(name = "fighter_id_1", referencedColumnName = "id", nullable = true)
    private Character fighter1;

    @OneToOne
    @JoinColumn(name = "fighter_id_2", referencedColumnName = "id", nullable = true)
    private Character fighter2;

    @OneToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "id", nullable = true)
    private Character winner;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    @JsonIgnore
    private Tournament tournoi;

    public Fight() {
    }

    public Fight(int number, Tournament tournament) {
        this.number = number;
        this.tournoi = tournament;
    }

    public Tournament getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournament tournament) {
        this.tournoi = tournament;
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

}
