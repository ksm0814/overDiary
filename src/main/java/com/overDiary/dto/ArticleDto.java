package com.overDiary.dto;

import com.overDiary.domain.Article;

import javax.validation.constraints.Size;

public class ArticleDto {

    @Size(min = 1, max = 50)
    private String title;

    @Size(min = 1)
    private String contents;

    @Size(min = 3)
    private String label;

    @Size(min = 6)
    private boolean openRange = true;

    public ArticleDto(){

    }

    public ArticleDto(String title, String contents, String label, boolean openRange) {
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

    public Article toArticle(){
        return new Article(title, contents, label, openRange);
    }
}
