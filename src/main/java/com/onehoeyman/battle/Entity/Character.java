package com.onehoeyman.battle.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "character")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Character {
    @ManyToMany
    @JoinTable(
            name = "equipment_owned",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    Set<Equipment> inventory;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hp")
    private int hp;

    @Column(name = "strength")
    private int strength;

    @Column(name = "intelligence")
    private int intelligence;

    @Column(name = "agility")
    private int agility;
    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = true)
    @JsonIgnore
    private Tournament tournament;
    @OneToOne(mappedBy = "fighter1", optional = true)
    @JsonIgnore
    private Fight fight1;
    @OneToOne(mappedBy = "fighter2", optional = true)
    @JsonIgnore
    private Fight fight2;
    @Column(name = "number_victory")
    private int numberVictory = 0;
    @Column(name = "tournament_victory")
    private int tournamentVictory = 0;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Equipment head;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Equipment chest;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Equipment leg;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Equipment hand;
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    @JsonIgnore
    private User creator;
    public Character() {

    }

    public int getTournamentVictory() {
        return tournamentVictory;
    }

    public void setTournamentVictory(int tournamentVictory) {
        this.tournamentVictory = tournamentVictory;
    }

    public int getNumberVictory() {
        return numberVictory;
    }

    public void setNumberVictory(int numberVictory) {
        this.numberVictory = numberVictory;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Equipment getLeg() {
        return leg;
    }

    public void setLeg(Equipment leg) {
        this.leg = leg;
    }

    public Equipment getHand() {
        return hand;
    }

    public void setHand(Equipment hand) {
        this.hand = hand;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public Set<Equipment> getInventory() {
        return inventory;
    }

    public void setInventory(Set<Equipment> inventory) {
        this.inventory = inventory;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Fight getFight1() {
        return fight1;
    }

    public void setFight1(Fight fight1) {
        this.fight1 = fight1;
    }

    public Fight getFight2() {
        return fight2;
    }

    public void setFight2(Fight fight2) {
        this.fight2 = fight2;
    }

    public Equipment getHead() {
        return head;
    }

    public void setHead(Equipment head) {
        this.head = head;
    }

    public Equipment getChest() {
        return chest;
    }

    public void setChest(Equipment chest) {
        this.chest = chest;
    }

    public int getTotalAgility() {
        int res = this.agility;
        res += leg != null ? leg.getAgilityBonus() : 0;
        res += chest != null ? chest.getAgilityBonus() : 0;
        res += hand != null ? hand.getAgilityBonus() : 0;
        res += head != null ? head.getAgilityBonus() : 0;
        return res;
    }

    public int getTotalHp() {
        int res = this.hp;
        res += leg != null ? leg.getHpBonus() : 0;
        res += chest != null ? chest.getHpBonus() : 0;
        res += hand != null ? hand.getHpBonus() : 0;
        res += head != null ? head.getHpBonus() : 0;
        return res;
    }

    public int getTotalStrength() {
        int res = this.strength;
        res += leg != null ? leg.getStrengthBonus() : 0;
        res += chest != null ? chest.getStrengthBonus() : 0;
        res += hand != null ? hand.getStrengthBonus() : 0;
        res += head != null ? head.getStrengthBonus() : 0;
        return res;
    }

    public int getTotalIntelligence() {
        int res = this.intelligence;
        res += leg != null ? leg.getIntelligenceBonus() : 0;
        res += chest != null ? chest.getIntelligenceBonus() : 0;
        res += hand != null ? hand.getIntelligenceBonus() : 0;
        res += head != null ? head.getIntelligenceBonus() : 0;
        return res;
    }

}
