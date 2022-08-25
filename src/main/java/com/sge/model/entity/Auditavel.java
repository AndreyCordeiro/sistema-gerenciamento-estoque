package com.sge.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@JsonIgnoreProperties(value = {"dataCriacao", "dataAtualizacao"}, allowGetters = true)
public abstract class Auditavel implements Serializable {
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataCriacao;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataAtualizacao;
}
