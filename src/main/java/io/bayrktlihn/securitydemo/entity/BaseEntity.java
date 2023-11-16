package io.bayrktlihn.securitydemo.entity;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    private boolean active = true;

    @Version
    private long version = 0;

    @Temporal(TemporalType.TIMESTAMP)
    private Date passiveDate;


    @PrePersist
    void prePersist() {
        createdDate = new Date();
    }

    @PreUpdate
    void preUpdate() {
        updatedDate = new Date();
    }


    public void makePassive() {
        active = false;
        passiveDate = new Date();
    }

}
