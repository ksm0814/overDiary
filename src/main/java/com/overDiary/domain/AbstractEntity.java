package com.overDiary.domain;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm")
    private long createdDate;

    @Column(name = "MODIFIED_DATE")
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm")
    private long modifiedDate;

    public AbstractEntity() {
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }
}
