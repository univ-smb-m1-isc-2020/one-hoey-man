package com.onehoeyman.battle.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "tournament")
    private Set<Character> participants;

    @OneToMany(mappedBy = "tournament")
    private Set<Fight> fights;
}
