package com.onehoeyman.battle.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tournament")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Tournament {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "max_size")
    private int maxSize =4;

    @Column(name = "number_of_participants")
    private int numberParticipants = 0;

    @Column(name = "round_number")
    private int roundNumber = 2;

    @OneToMany(mappedBy = "tournament")
    private Set<Character> participants;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private Set<Fight> fights;

    public Tournament() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getNumberParticipants() {
        return numberParticipants;
    }

    public void setNumberParticipants(int numberParticipants) {
        this.numberParticipants = numberParticipants;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Set<Character> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Character> participants) {
        this.participants = participants;
    }

    public void addFight(Fight fight) {
        this.fights.add(fight);
    }

    public Set<Fight> getFights() {
        return fights;
    }

    public void setFights(Set<Fight> fights) {
        this.fights = fights;
    }

    public void registerParticipants(Character character) {
        if (numberParticipants < maxSize) {
            this.participants.add(character);

            int numberCombat = numberParticipants / 2 + 1;
            for (Fight fight :
                    this.fights) {
                if (fight.getNumber() == numberCombat) {
                    if(this.numberParticipants % 2 == 0)
                        fight.setFighter1(character);
                    else
                        fight.setFighter2(character);
                }
            }
            character.setTournament(this);
            numberParticipants += 1;

        }
    }
}
