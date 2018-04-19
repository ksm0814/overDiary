package com.overDiary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;

@Entity
public class Attachment {
    // TODO Attachment class, repository, service, controller 만들고 Article에 관계 매핑하기
    // TODO Attachment는 추후에 명예의 사진전당에도 쓰일 예정
    // TODO Article 저장 테스트 코드 생성
    // TODO Article을 저장하는데서 upload 부분은 articlecontroller에 연결되게 따로 upload 버튼 만들기

    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue
    private long AttachmentKey;

    @Column(nullable = false)
    File file;

    @Column(nullable = false)
    String fileName;

    @Column
    String filePath;

    public Attachment(){}

    public Attachment(String fileName, String filePath, File file) {
        this.file = file;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public long getAttachmentKey() {
        return AttachmentKey;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    public boolean isSameName(String oldName) {
        return fileName.equals(oldName);
    }
}
