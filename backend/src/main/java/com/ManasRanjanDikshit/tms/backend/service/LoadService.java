package com.ManasRanjanDikshit.tms.backend.service;

import com.ManasRanjanDikshit.tms.backend.dto.LoadRequestDTO;
import com.ManasRanjanDikshit.tms.backend.dto.LoadResponseDTO;
import com.ManasRanjanDikshit.tms.backend.entity.Load;
import com.ManasRanjanDikshit.tms.backend.entity.LoadStatus;
import com.ManasRanjanDikshit.tms.backend.exception.InvalidStatusTransitionException;
import com.ManasRanjanDikshit.tms.backend.exception.ResourceNotFoundException;
import com.ManasRanjanDikshit.tms.backend.repository.LoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoadService {
    private final LoadRepository loadRepository;

    @Transactional
    public LoadResponseDTO createLoad(LoadRequestDTO dto) {
        Load load = Load.builder()
                .shipperId(dto.getShipperId())
                .loadingCity(dto.getLoadingCity())
                .unloadingCity(dto.getUnloadingCity())
                .loadingDate(dto.getLoadingDate())
                .productType(dto.getProductType())
                .weight(dto.getWeight())
                .weightUnit(dto.getWeightUnit())
                .truckType(dto.getTruckType())
                .noOfTrucks(dto.getNoOfTrucks())
                .status(LoadStatus.POSTED)
                .build();
        load = loadRepository.save(load);
        return toResponseDTO(load);
    }

    @Transactional
    public void cancelLoad(UUID loadId) {
        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));
        if (load.getStatus() == LoadStatus.BOOKED) {
            throw new InvalidStatusTransitionException("Cannot cancel a BOOKED load.");
        }
        if (load.getStatus() == LoadStatus.CANCELLED) {
            throw new InvalidStatusTransitionException("Load is already cancelled.");
        }
        load.setStatus(LoadStatus.CANCELLED);
        loadRepository.save(load);
    }

    public Page<LoadResponseDTO> getLoadsByShipperAndStatus(String shipperId, LoadStatus status, Pageable pageable) {
        return loadRepository.findByShipperIdAndStatus(shipperId, status, pageable)
                .map(this::toResponseDTO);
    }

    public LoadResponseDTO getLoadById(UUID loadId) {
        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));
        return toResponseDTO(load);
    }

    private LoadResponseDTO toResponseDTO(Load load) {
        return LoadResponseDTO.builder()
                .loadId(load.getLoadId())
                .shipperId(load.getShipperId())
                .loadingCity(load.getLoadingCity())
                .unloadingCity(load.getUnloadingCity())
                .loadingDate(load.getLoadingDate())
                .productType(load.getProductType())
                .weight(load.getWeight())
                .weightUnit(load.getWeightUnit())
                .truckType(load.getTruckType())
                .noOfTrucks(load.getNoOfTrucks())
                .status(load.getStatus())
                .datePosted(load.getDatePosted())
                .build();
    }
}
