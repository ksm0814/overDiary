package com.overDiary.domain;

import javax.persistence.*;

@Entity
public class Article extends AbstractEntity {

    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue
    private long ArticleKey;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private boolean openRange = true;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_article_writer"))
    private User writer;

    public Article(){

    }

    public Article(String title, String contents, String label, boolean openRange) {
        this.title = title;
        this.contents = contents;
        this.label = label;
        this.openRange = openRange;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isOpenRange() {
        return openRange;
    }

    public void setOpenRange(boolean openRange) {
        this.openRange = openRange;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Article{" +
                "ArticleKey=" + ArticleKey +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", label='" + label + '\'' +
                ", openRange=" + openRange +
                ", writer=" + writer +
                '}';
    }
}
