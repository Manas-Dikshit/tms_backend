package com.ManasRanjanDikshit.tms.backend.dto;

import com.ManasRanjanDikshit.tms.backend.entity.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class BookingResponseDTO {
    private UUID bookingId;
    private UUID loadId;
    private UUID bidId;
    private UUID transporterId;
    private int allocatedTrucks;
    private double finalRate;
    private BookingStatus status;
    private Timestamp bookedAt;
}
