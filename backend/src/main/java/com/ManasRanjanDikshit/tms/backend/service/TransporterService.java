package com.ManasRanjanDikshit.tms.backend.service;

import com.ManasRanjanDikshit.tms.backend.dto.TransporterRequestDTO;
import com.ManasRanjanDikshit.tms.backend.dto.TransporterResponseDTO;
import com.ManasRanjanDikshit.tms.backend.entity.Transporter;
import com.ManasRanjanDikshit.tms.backend.exception.ResourceNotFoundException;
import com.ManasRanjanDikshit.tms.backend.repository.TransporterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransporterService {
    private final TransporterRepository transporterRepository;

    @Transactional
    public TransporterResponseDTO registerTransporter(TransporterRequestDTO dto) {
        Transporter transporter = Transporter.builder()
                .companyName(dto.getCompanyName())
                .rating(dto.getRating())
                .availableTrucks(dto.getAvailableTrucks())
                .build();
        transporter = transporterRepository.save(transporter);
        return toResponseDTO(transporter);
    }

    @Transactional
    public TransporterResponseDTO updateAvailableTrucks(UUID transporterId, Map<String, Integer> availableTrucks) {
        Transporter transporter = transporterRepository.findByTransporterId(transporterId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter not found"));
        transporter.setAvailableTrucks(availableTrucks);
        transporter = transporterRepository.save(transporter);
        return toResponseDTO(transporter);
    }

    public TransporterResponseDTO getTransporterDetails(UUID transporterId) {
        Transporter transporter = transporterRepository.findByTransporterId(transporterId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter not found"));
        return toResponseDTO(transporter);
    }

    private TransporterResponseDTO toResponseDTO(Transporter transporter) {
        return TransporterResponseDTO.builder()
                .transporterId(transporter.getTransporterId())
                .companyName(transporter.getCompanyName())
                .rating(transporter.getRating())
                .availableTrucks(transporter.getAvailableTrucks())
                .build();
    }
}
