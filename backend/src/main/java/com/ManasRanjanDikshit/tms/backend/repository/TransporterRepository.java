package com.ManasRanjanDikshit.tms.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ManasRanjanDikshit.tms.backend.entity.Transporter;

public interface TransporterRepository extends JpaRepository<Transporter, UUID> {
    Optional<Transporter> findByTransporterId(UUID transporterId);
}
