package com.ManasRanjanDikshit.tms.backend.dto;

import java.sql.Timestamp;

import com.ManasRanjanDikshit.tms.backend.entity.WeightUnit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoadRequestDTO {
    private String shipperId;
    private String loadingCity;
    private String unloadingCity;
    private Timestamp loadingDate;
    private String productType;
    private double weight;
    private WeightUnit weightUnit;
    private String truckType;
    private int noOfTrucks;
}
