package com.onehoeyman.battle.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tournament")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tournament {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "max_size")
    private int maxSize = 4;

    @Column(name = "number_of_participants")
    private int numberParticipants = 0;

    @Column(name = "round_number")
    private int roundNumber = 2;

    @OneToMany(mappedBy = "tournament")
    @JsonIgnore
    private Set<Character> participants;

    @OneToMany(mappedBy = "tournoi", cascade = CascadeType.ALL)
    private Set<Fight> fights;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.NotStarted;

    public Tournament() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

            character.setHead(null);
            character.setLeg(null);
            character.setChest(null);
            character.setHand(null);

            int numberCombat = (int) (maxSize - 1 - Math.floor((double)(numberParticipants / 2)));
            for (Fight fight :
                    this.fights) {
                if (fight.getNumber() == numberCombat) {
                    if (this.numberParticipants % 2 == 0) {
                        fight.setFighter1(character);
                        character.setFight1(fight);
                    }

                    else {
                        fight.setFighter2(character);
                        character.setFight2(fight);
                    }
                }
            }
            character.setTournament(this);
            numberParticipants += 1;

        }
    }

}
