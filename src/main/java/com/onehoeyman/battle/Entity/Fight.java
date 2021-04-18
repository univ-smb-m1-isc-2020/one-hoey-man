package com.onehoeyman.battle.Entity;

import javax.persistence.*;

@Entity
@Table(name = "fight")
public class Fight {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
    private Tournament tournament;


}
