package com.ManasRanjanDikshit.tms.backend.controller;

import com.ManasRanjanDikshit.tms.backend.dto.BidRequestDTO;
import com.ManasRanjanDikshit.tms.backend.dto.BidResponseDTO;
import com.ManasRanjanDikshit.tms.backend.entity.BidStatus;
import com.ManasRanjanDikshit.tms.backend.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bid")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;

    @PostMapping
    public ResponseEntity<BidResponseDTO> submitBid(@RequestBody BidRequestDTO dto) {
        return ResponseEntity.ok(bidService.submitBid(dto));
    }

    @GetMapping
    public ResponseEntity<List<BidResponseDTO>> getBids(@RequestParam UUID loadId,
                                                        @RequestParam UUID transporterId,
                                                        @RequestParam BidStatus status) {
        return ResponseEntity.ok(bidService.getBids(loadId, transporterId, status));
    }

    @GetMapping("/{bidId}")
    public ResponseEntity<BidResponseDTO> getBid(@PathVariable UUID bidId) {
        return ResponseEntity.ok(bidService.getBidById(bidId));
    }

    @PatchMapping("/{bidId}/reject")
    public ResponseEntity<Void> rejectBid(@PathVariable UUID bidId) {
        bidService.rejectBid(bidId);
        return ResponseEntity.noContent().build();
    }
}
