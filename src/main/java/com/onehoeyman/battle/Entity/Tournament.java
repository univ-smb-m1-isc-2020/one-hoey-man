package com.onehoeyman.battle.Entity;

import javax.persistence.*;
import java.util.HashMap;
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

    @Column(name = "max_size")
    private int maxSize;
    
    @OneToMany(mappedBy = "tournament")
    private Set<Character> participants;

    @OneToMany(mappedBy = "tournament")
    private HashMap<Integer,Fight> fights;
}
