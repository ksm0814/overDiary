package com.overDiary.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rank extends AbstractEntity {
    private static final Logger log = LoggerFactory.getLogger(Rank.class);

    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue
    private long rankKey;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String players;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String levels;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String kds;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String ratios;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String mostHeroes;

    public Rank() {

    }


    public Rank(String names, String levels, String kds, String ratios, String mostHeroes) {
        this.players = names;
        this.levels = levels;
        this.kds = kds;
        this.ratios = ratios;
        this.mostHeroes = mostHeroes;
    }


    public long getRankKey() {
        return rankKey;
    }

    public String[] getPlayers() {
        String[] temp = players.split(" ");

        return temp;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String[] getLevels() {
        String[] temp = levels.split("Lv. ");

        return temp;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String[] getKds() {
        return kds.split(" : 1");
    }

    public void setKds(String kds) {
        this.kds = kds;
    }

    public String[] getRatios() {
        return ratios.split(" ");
    }

    public void setRatios(String ratios) {
        this.ratios = ratios;
    }

    public List<String> getMostHeroes() {
        String[] temp = mostHeroes.split(",");
        List<String> heroes = new ArrayList<>();
        for (int i = 0; i < temp.length;) {
            heroes.add(temp[i] + ", " + temp[i+1] + ", "+ temp[i+2]);
            i += 3;
        }
       return heroes;
    }

    public void setMostHeroes(String mostHeroes) {
        this.mostHeroes = mostHeroes;
    }

    public Rank update(String names, String levels, String kds, String ratios, String mostHeroes) {
        this.players = names;
        this.levels = levels;
        this.kds = kds;
        this.ratios = ratios;
        this.mostHeroes = mostHeroes;
        return this;
    }
}
