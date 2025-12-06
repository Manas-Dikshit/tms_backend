package com.ManasRanjanDikshit.tms.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "transporters")
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
