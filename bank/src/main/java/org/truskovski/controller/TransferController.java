package org.truskovski.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.truskovski.model.transfer.dto.TransferDTO;
import org.truskovski.service.TransferService;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody TransferDTO request) {
        transferService.performTransfer(
                request.senderPhone(),
                request.receiverPhone(),
                request.amount()
        );
        return ResponseEntity.ok("Transfer successful ✅");
    }
}