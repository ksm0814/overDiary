package overdiary.domain;

import javax.persistence.*;

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
    private long articleId;

    Alarm(){}

    public Alarm(String message, User targetUser, long articleId) {
        this.message =  message ;
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

}
