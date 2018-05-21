package overdiary.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rank extends AbstractEntity {
    private static final Logger log = LoggerFactory.getLogger(Rank.class);

    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue
    private long rankKey;

    @Column
    private String players;

    @Column
    private String levels;

    @Column
    private String scores;

    @Column
    private String fireTimes;

    @Column
    private String kds;

    @Column
    private String mostHeroes;

    public Rank() {

    }

    public Rank(String players, String levels, String scores, String fireTimes, String kds, String mostHeroes) {
        this.players = players;
        this.levels = levels;
        this.scores = scores;
        this.fireTimes = fireTimes;
        this.kds = kds;
        this.mostHeroes = mostHeroes;
    }

    public long getRankKey() {
        return rankKey;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getFireTimes() {
        return fireTimes;
    }

    public void setFireTimes(String fireTimes) {
        this.fireTimes = fireTimes;
    }

    public String getKds() {
        return kds;
    }

    public void setKds(String kds) {
        this.kds = kds;
    }

    public String getMostHeroes() {
        return mostHeroes;
    }

    public void setMostHeroes(String mostHeroes) {
        this.mostHeroes = mostHeroes;
    }

    public void update(String players, String levels, String scores, String fireTimes, String kds, String mostHeroes) {
        this.players = players;
        this.levels = levels;
        this.scores = scores;
        this.fireTimes = fireTimes;
        this.kds = kds;
        this.mostHeroes = mostHeroes;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "rankKey=" + rankKey +
                ", players='" + players + '\'' +
                ", levels='" + levels + '\'' +
                ", scores='" + scores + '\'' +
                ", fireTimes='" + fireTimes + '\'' +
                ", kds='" + kds + '\'' +
                ", mostHeroes='" + mostHeroes + '\'' +
                '}';
    }
}
