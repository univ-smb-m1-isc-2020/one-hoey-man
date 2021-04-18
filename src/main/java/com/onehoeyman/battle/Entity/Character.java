package com.onehoeyman.battle.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "character")
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
    @Column(name = "max_hp")
    private int maxHp;
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
    private Tournament tournament;

    @OneToOne(mappedBy = "fighter1", optional = true)
    private Fight fight1;
    @OneToOne(mappedBy = "fighter2", optional = true)
    private Fight fight2;
    @OneToOne(mappedBy = "winner", optional = true)
    private Fight fightVictory;

    /* @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "chest_id", referencedColumnName = "id", nullable = true)
     private Equipment chest;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "head_id", referencedColumnName = "id", nullable = true)
     private Equipment head;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "hand_id", referencedColumnName = "id", nullable = true)
     private Equipment hand;

     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "leg_id", referencedColumnName = "id", nullable = true)
     private Equipment leg;*/
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Equipment head;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Equipment chest;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Equipment leg;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    private Equipment hand;

    public Character() {

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

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
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
}
