package overdiary.domain;

import javax.persistence.*;

@Entity
public class Alarm extends AbstractEntity {
    private static final String ALARM_MESSAGE = "새 글이 등록되었습니다! ";

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
        this.message = ALARM_MESSAGE + message ;
        this.targetUser = targetUser;
        this.articleId = articleId;
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

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
