package com.ManasRanjanDikshit.tms.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BidRequestDTO {
    private UUID loadId;
    private UUID transporterId;
    private double proposedRate;
    private int trucksOffered;
}
