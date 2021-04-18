package com.onehoeyman.battle.Entity;

import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "max_size")
    private int maxSize = 32;

    @Column(name = "number_of_participants")
    private int numberParticipants = 0;

    @Column(name = "roundNumber")
    private int roundNumber = 6;

    @OneToMany(mappedBy = "tournament")
    private Set<Character> participants;

    @OneToMany(mappedBy = "tournament")
    private Set<Fight> fights;
    public Tournament(){
    }
}
