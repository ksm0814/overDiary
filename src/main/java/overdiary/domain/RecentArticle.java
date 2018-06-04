package overdiary.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecentArticle {

    @Column
    private String title;

    @Column
    private boolean isRead;

    public RecentArticle(){}

    public RecentArticle(String title, boolean isRead) {
        this.title = title;
        this.isRead = isRead;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "RecentArticle{" +
                "title='" + title + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
