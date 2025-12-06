package com.ManasRanjanDikshit.tms.backend.repository;

import com.ManasRanjanDikshit.tms.backend.entity.Load;
import com.ManasRanjanDikshit.tms.backend.entity.LoadStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LoadRepository extends JpaRepository<Load, UUID> {
    Page<Load> findByShipperIdAndStatus(String shipperId, LoadStatus status, Pageable pageable);

    @Query("SELECT l FROM Load l WHERE l.status IN ('POSTED', 'OPEN_FOR_BIDS')")
    List<Load> findLoadsWithActiveBids();
}
