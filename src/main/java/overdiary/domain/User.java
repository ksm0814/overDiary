package overdiary.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends AbstractEntity {
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
