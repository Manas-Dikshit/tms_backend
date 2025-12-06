package com.ManasRanjanDikshit.tms.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "bids",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"load_id", "transporter_id"})})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "bid_id", updatable = false, nullable = false)
    private UUID bidId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "load_id", nullable = false)
    private Load load;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transporter_id", nullable = false)
    private Transporter transporter;

    @Column(nullable = false)
    private double proposedRate;

    @Column(nullable = false)
    private int trucksOffered;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BidStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp submittedAt;
}
