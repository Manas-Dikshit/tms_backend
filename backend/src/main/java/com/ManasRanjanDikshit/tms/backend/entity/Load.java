package com.ManasRanjanDikshit.tms.backend.entity;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "load")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "load_id", updatable = false, nullable = false)
    private UUID loadId;

    @Column(nullable = false)
    private String shipperId;

    @Column(nullable = false)
    private String loadingCity;

    @Column(nullable = false)
    private String unloadingCity;

    @Column(nullable = false)
    private Timestamp loadingDate;

    @Column(nullable = false)
    private String productType;

    @Column(nullable = false)
    private double weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WeightUnit weightUnit;

    @Column(nullable = false)
    private String truckType;

    @Column(nullable = false)
    private int noOfTrucks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoadStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp datePosted;

    @Version
    private Long version;
}
