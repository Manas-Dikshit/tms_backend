package com.ManasRanjanDikshit.tms.backend.service;

import com.ManasRanjanDikshit.tms.backend.dto.BookingRequestDTO;
import com.ManasRanjanDikshit.tms.backend.dto.BookingResponseDTO;
import com.ManasRanjanDikshit.tms.backend.entity.*;
import com.ManasRanjanDikshit.tms.backend.exception.*;
import com.ManasRanjanDikshit.tms.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BidRepository bidRepository;
    private final LoadRepository loadRepository;
    private final TransporterRepository transporterRepository;

    @Transactional
    public BookingResponseDTO acceptBidAndCreateBooking(BookingRequestDTO dto) {
        Load load = loadRepository.findById(dto.getLoadId())
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));
        Bid bid = bidRepository.findByBidId(dto.getBidId())
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found"));
        Transporter transporter = transporterRepository.findByTransporterId(dto.getTransporterId())
                .orElseThrow(() -> new ResourceNotFoundException("Transporter not found"));
        int available = transporter.getAvailableTrucks().getOrDefault(load.getTruckType(), 0);
        if (dto.getAllocatedTrucks() > available) {
            throw new InsufficientCapacityException("Not enough trucks available for booking.");
        }
        // Deduct allocated trucks
        Map<String, Integer> trucks = transporter.getAvailableTrucks();
        trucks.put(load.getTruckType(), available - dto.getAllocatedTrucks());
        transporter.setAvailableTrucks(trucks);
        transporterRepository.save(transporter);
        // Create booking
        Booking booking = Booking.builder()
                .load(load)
                .bid(bid)
                .transporter(transporter)
                .allocatedTrucks(dto.getAllocatedTrucks())
                .finalRate(dto.getFinalRate())
                .status(BookingStatus.CONFIRMED)
                .build();
        booking = bookingRepository.save(booking);
        return toResponseDTO(booking);
    }

    @Transactional
    public void cancelBooking(UUID bookingId) {
        Booking booking = bookingRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new InvalidStatusTransitionException("Booking is already cancelled.");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        // Restore trucks
        Transporter transporter = booking.getTransporter();
        Load load = booking.getLoad();
        int current = transporter.getAvailableTrucks().getOrDefault(load.getTruckType(), 0);
        transporter.getAvailableTrucks().put(load.getTruckType(), current + booking.getAllocatedTrucks());
        transporterRepository.save(transporter);
        bookingRepository.save(booking);
    }

    public BookingResponseDTO getBookingById(UUID bookingId) {
        Booking booking = bookingRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return toResponseDTO(booking);
    }

    private BookingResponseDTO toResponseDTO(Booking booking) {
        return BookingResponseDTO.builder()
                .bookingId(booking.getBookingId())
                .loadId(booking.getLoad().getLoadId())
                .bidId(booking.getBid().getBidId())
                .transporterId(booking.getTransporter().getTransporterId())
                .allocatedTrucks(booking.getAllocatedTrucks())
                .finalRate(booking.getFinalRate())
                .status(booking.getStatus())
                .bookedAt(booking.getBookedAt())
                .build();
    }
}
