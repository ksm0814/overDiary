package overdiary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Alarm extends AbstractEntity {

    @Column
    @Id
    @GeneratedValue
    private long alarmKey;

    @Column
    private String message;

    @ManyToOne
    @JoinColumn
    private User targetUser;

    @Column
    private boolean isOpened = false;

    @Column
    private long articleId;


    Alarm() {
    }

    public Alarm(String message, User targetUser, long articleId) {
        this.message = message;
        this.targetUser = targetUser;
        this.articleId = articleId;
    }

    public long getAlarmKey() {
        return alarmKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public long getArticleId() {
        return articleId;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "alarmKey=" + alarmKey +
                ", message='" + message + '\'' +
                ", targetUser=" + targetUser +
                ", isOpened=" + isOpened +
                ", articleId=" + articleId +
                '}';
    }
}
