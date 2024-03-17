package com.Jala.B2Laboratorio2.entities;

import jakarta.persistence.*;
import lombok.Builder;
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
    private Date createdAt;
    @Setter
    @Column(name = "DT_UPDATED_AT")
    private Date updatedAt;
    @Setter
    @Column(name = "ST_ACTIVE")
    private boolean active = true;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
            this.updatedAt = createdAt;
        }
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "uuid=" + uuid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", active=" + active +
                '}';
    }
}
