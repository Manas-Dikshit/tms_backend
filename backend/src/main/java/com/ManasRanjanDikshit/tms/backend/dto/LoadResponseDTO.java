package com.ManasRanjanDikshit.tms.backend.dto;

import com.ManasRanjanDikshit.tms.backend.entity.LoadStatus;
import com.ManasRanjanDikshit.tms.backend.entity.WeightUnit;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class LoadResponseDTO {
    private UUID loadId;
    private String shipperId;
    private String loadingCity;
    private String unloadingCity;
    private Timestamp loadingDate;
    private String productType;
    private double weight;
    private WeightUnit weightUnit;
    private String truckType;
    private int noOfTrucks;
    private LoadStatus status;
    private Timestamp datePosted;
}
