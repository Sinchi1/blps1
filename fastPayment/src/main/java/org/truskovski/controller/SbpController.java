package org.truskovski.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.truskovski.model.transferRequest.dto.TransferRequestDTO;
import org.truskovski.service.SbpService;

@RestController
@RequestMapping("/sbp/transfer")
@RequiredArgsConstructor
public class SbpController {

    private final SbpService sbpService;

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDTO request) {
        return sbpService.processTransfer(request);
    }
}