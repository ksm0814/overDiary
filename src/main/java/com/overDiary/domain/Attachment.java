package com.overDiary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;

@Entity
public class Attachment {

    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue
    private long attachmentKey;

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
        return attachmentKey;
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
