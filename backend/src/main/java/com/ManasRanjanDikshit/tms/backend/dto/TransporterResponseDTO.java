package com.ManasRanjanDikshit.tms.backend.dto;

import java.util.Map;
import java.util.UUID;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransporterResponseDTO {
    private UUID transporterId;
    private String companyName;
    private double rating;
    private Map<String, Integer> availableTrucks;
}
