package com.ManasRanjanDikshit.tms.backend.dto;

import java.util.Map;

import lombok.Builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransporterRequestDTO {
    private String companyName;
    private double rating;
    private Map<String, Integer> availableTrucks;
}
