package com.ManasRanjanDikshit.tms.backend.controller;

import com.ManasRanjanDikshit.tms.backend.dto.TransporterRequestDTO;
import com.ManasRanjanDikshit.tms.backend.dto.TransporterResponseDTO;
import com.ManasRanjanDikshit.tms.backend.service.TransporterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/transporter")
@RequiredArgsConstructor
public class TransporterController {
    private final TransporterService transporterService;

    @PostMapping
    public ResponseEntity<TransporterResponseDTO> registerTransporter(@RequestBody TransporterRequestDTO dto) {
        return ResponseEntity.ok(transporterService.registerTransporter(dto));
    }

    @GetMapping("/{transporterId}")
    public ResponseEntity<TransporterResponseDTO> getTransporter(@PathVariable UUID transporterId) {
        return ResponseEntity.ok(transporterService.getTransporterDetails(transporterId));
    }

    @PutMapping("/{transporterId}/trucks")
    public ResponseEntity<TransporterResponseDTO> updateTrucks(@PathVariable UUID transporterId,
                                                               @RequestBody Map<String, Integer> availableTrucks) {
        return ResponseEntity.ok(transporterService.updateAvailableTrucks(transporterId, availableTrucks));
    }
}
