package com.ManasRanjanDikshit.tms.backend.service;

import com.ManasRanjanDikshit.tms.backend.dto.BidRequestDTO;
import com.ManasRanjanDikshit.tms.backend.dto.BidResponseDTO;
import com.ManasRanjanDikshit.tms.backend.entity.*;
import com.ManasRanjanDikshit.tms.backend.exception.*;
import com.ManasRanjanDikshit.tms.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository = null;
    private final LoadRepository loadRepository;
    private final TransporterRepository transporterRepository;

    @Transactional
    public BidResponseDTO submitBid(BidRequestDTO dto) {
        Load load = loadRepository.findById(dto.getLoadId())
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));
        Transporter transporter = transporterRepository.findByTransporterId(dto.getTransporterId())
                .orElseThrow(() -> new ResourceNotFoundException("Transporter not found"));
        if (!(load.getStatus() == LoadStatus.POSTED || load.getStatus() == LoadStatus.OPEN_FOR_BIDS)) {
            throw new InvalidStatusTransitionException("Cannot bid on load with status: " + load.getStatus());
        }
        int available = transporter.getAvailableTrucks().getOrDefault(load.getTruckType(), 0);
        if (dto.getTrucksOffered() > available) {
            throw new InsufficientCapacityException("Not enough trucks available.");
        }
        Bid bid = Bid.builder()
                .load(load)
                .transporter(transporter)
                .proposedRate(dto.getProposedRate())
                .trucksOffered(dto.getTrucksOffered())
                .status(BidStatus.PENDING)
                .build();
        bid = bidRepository.save(bid);
        return toResponseDTO(bid);
    }

    @Transactional
    public void rejectBid(UUID bidId) {
        Bid bid = bidRepository.findByBidId(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found"));
        bid.setStatus(BidStatus.REJECTED);
        bidRepository.save(bid);
    }

    public List<BidResponseDTO> getBids(UUID loadId, UUID transporterId, BidStatus status) {
        List<Bid> bids = bidRepository.findByLoad_LoadIdAndTransporter_TransporterIdAndStatus(loadId, transporterId, status);
        return bids.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public BidResponseDTO getBidById(UUID bidId) {
        Bid bid = bidRepository.findByBidId(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found"));
        return toResponseDTO(bid);
    }

    private BidResponseDTO toResponseDTO(Bid bid) {
        return BidResponseDTO.builder()
                .bidId(bid.getBidId())
                .loadId(bid.getLoad().getLoadId())
                .transporterId(bid.getTransporter().getTransporterId())
                .proposedRate(bid.getProposedRate())
                .trucksOffered(bid.getTrucksOffered())
                .status(bid.getStatus())
                .submittedAt(bid.getSubmittedAt())
                .build();
    }
}
