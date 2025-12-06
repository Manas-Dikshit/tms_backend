package com.ManasRanjanDikshit.tms.backend.controller;

import com.ManasRanjanDikshit.tms.backend.dto.LoadRequestDTO;
import com.ManasRanjanDikshit.tms.backend.dto.LoadResponseDTO;
import com.ManasRanjanDikshit.tms.backend.entity.LoadStatus;
import com.ManasRanjanDikshit.tms.backend.service.LoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
public class LoadController {
    private final LoadService loadService;

    @PostMapping
    public ResponseEntity<LoadResponseDTO> createLoad(@RequestBody LoadRequestDTO dto) {
        return ResponseEntity.ok(loadService.createLoad(dto));
    }

    @GetMapping
    public ResponseEntity<Page<LoadResponseDTO>> getLoads(@RequestParam String shipperId,
                                                          @RequestParam LoadStatus status,
                                                          Pageable pageable) {
        return ResponseEntity.ok(loadService.getLoadsByShipperAndStatus(shipperId, status, pageable));
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<LoadResponseDTO> getLoad(@PathVariable UUID loadId) {
        return ResponseEntity.ok(loadService.getLoadById(loadId));
    }

    @PatchMapping("/{loadId}/cancel")
    public ResponseEntity<Void> cancelLoad(@PathVariable UUID loadId) {
        loadService.cancelLoad(loadId);
        return ResponseEntity.noContent().build();
    }
}
