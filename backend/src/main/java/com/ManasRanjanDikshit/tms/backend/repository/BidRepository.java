package com.ManasRanjanDikshit.tms.backend.repository;

import com.ManasRanjanDikshit.tms.backend.entity.Bid;
import com.ManasRanjanDikshit.tms.backend.entity.BidStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BidRepository extends JpaRepository<Bid, UUID> {
    List<Bid> findByLoad_LoadId(UUID loadId);
    List<Bid> findByTransporter_TransporterId(UUID transporterId);
    List<Bid> findByLoad_LoadIdAndTransporter_TransporterIdAndStatus(UUID loadId, UUID transporterId, BidStatus status);
    Optional<Bid> findByBidId(UUID bidId);
}
