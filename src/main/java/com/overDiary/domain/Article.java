package com.overDiary.domain;

import com.overDiary.dto.ArticleDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Article extends AbstractEntity {

    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue
    private long ArticleKey;

    @Column
    private String title;

    @Column(columnDefinition="LONGTEXT")
    private String contents;

    @Column
    private String label;

    @Column
    private boolean openRange = true;

    @Column
    private int views = 0;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_article_attach"))
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Attachment> attachments;

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

    public long getArticleKey() {
        return ArticleKey;
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

    public int getViews() {
        return views;
    }

    public void setViews() {
        this.views = getViews() + 1;
    }

    public void addFile(Attachment attachment) {
        attachments.add(attachment);
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setDto(ArticleDto articleDto) {
        this.title = articleDto.getTitle();
        this.contents = articleDto.getContents();
        this.label = articleDto.getLabel();
        this.openRange = articleDto.isOpenRange();
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
