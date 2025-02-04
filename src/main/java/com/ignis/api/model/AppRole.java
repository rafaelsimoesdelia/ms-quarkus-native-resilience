package com.ignis.api.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "APP_ROLE")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "approle_sequence", initialValue = 1, allocationSize = 1, sequenceName = "ROLE_SEQ")
public class AppRole extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa IDENTITY ao invés de SEQUENCE
    @Column(name = "ROLE_ID", nullable = false)
    private Long id;

    private String name;
}