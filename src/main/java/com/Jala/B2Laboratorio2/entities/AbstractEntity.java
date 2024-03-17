package com.Jala.B2Laboratorio2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", updatable = false)
    private UUID uuid;
    @Column(name = "DT_CREATED_AT", updatable = false)
    private final Date createdAt = new Date();
    @Setter
    @Column(name = "DT_UPDATED_AT")
    private Date updatedAt;
    @Setter
    @Column(name = "ST_ACTIVE")
    private boolean active = true;

    @PrePersist
    private void prePersist() {
        updatedAt = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = new Date();
    }
}
