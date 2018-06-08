package overdiary.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import overdiary.helper.SchedulerConfig;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends AbstractEntity {
    private static final Logger log = LoggerFactory.getLogger(User.class);
    public static final GuestUser GUEST_USER = new GuestUser();

    @Column(unique = true, nullable = false, name = "USERKEY")
    @Id
    @GeneratedValue
    private long userKey;

    @Column
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column
    @Length(min = 4, max = 20)
    private String password;

    @Column
    private String email;

    @Column
    private String gameId;

    @Column
    private String gameBattletag;

    @Column
    private String chartHeroes;

    @Column
    private String chartPlayTime;


    public User(){

    }
    public User(String userId, String name, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public long getUserKey() {
        return userKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameBattletag() {
        return gameBattletag;
    }

    public void setGameBattletag(String gameBattletag) {
        this.gameBattletag = gameBattletag;
    }

    public String getChartHeroes() {
        return chartHeroes;
    }

    public void setChartHeroes(String chartHeroes) {
        this.chartHeroes = chartHeroes;
    }

    public String getChartPlayTime() {
        return chartPlayTime;
    }

    public void setChartPlayTime(String chartPlayTime) {
        this.chartPlayTime = chartPlayTime;
    }

    public boolean isSamePassword(String password){
        return this.password.equals(password);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userKey == user.userKey &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(gameId, user.gameId) &&
                Objects.equals(gameBattletag, user.gameBattletag) &&
                Objects.equals(chartHeroes, user.chartHeroes) &&
                Objects.equals(chartPlayTime, user.chartPlayTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userKey, userId, name, password, email, gameId, gameBattletag, chartHeroes, chartPlayTime);
    }

    @JsonIgnore
    public boolean isGuestUser() {
        return false;
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }
    @Override
    public String toString() {
        return "User{" +
                "userKey=" + userKey +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
