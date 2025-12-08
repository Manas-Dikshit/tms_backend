package com.ManasRanjanDikshit.tms.backend.entity;

import java.util.Map;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transporter")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transporter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transporter_id", updatable = false, nullable = false)
    private UUID transporterId;

    @Column(nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false)
    private double rating;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "transporter_trucks", joinColumns = @JoinColumn(name = "transporter_id"))
    @MapKeyColumn(name = "truck_type")
    @Column(name = "count")
    private Map<String, Integer> availableTrucks;
}
