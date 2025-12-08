package com.ManasRanjanDikshit.tms.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ManasRanjanDikshit.tms.backend.dto.BidResponseDTO;
import com.ManasRanjanDikshit.tms.backend.dto.LoadRequestDTO;
import com.ManasRanjanDikshit.tms.backend.dto.LoadResponseDTO;
import com.ManasRanjanDikshit.tms.backend.entity.LoadStatus;
import com.ManasRanjanDikshit.tms.backend.service.LoadService;

import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<Page<LoadResponseDTO>> getLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) LoadStatus status,
            Pageable pageable) {
        if (shipperId != null && status != null) {
            return ResponseEntity.ok(loadService.getLoadsByShipperAndStatus(shipperId, status, pageable));
        } else {
            // Fallback: return all loads paginated if params are missing
            return ResponseEntity.ok(loadService.getAllLoads(pageable));
        }
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

    @GetMapping("/{loadId}/best-bids")
    public ResponseEntity<List<BidResponseDTO>> getBestBids(@PathVariable UUID loadId) {
        return ResponseEntity.ok(loadService.getBestBidsForLoad(loadId));
    }
}
