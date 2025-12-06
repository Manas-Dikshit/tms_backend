package com.ManasRanjanDikshit.tms.backend.dto;

import com.ManasRanjanDikshit.tms.backend.entity.BidStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class BidResponseDTO {
    private UUID bidId;
    private UUID loadId;
    private UUID transporterId;
    private double proposedRate;
    private int trucksOffered;
    private BidStatus status;
    private Timestamp submittedAt;
}
