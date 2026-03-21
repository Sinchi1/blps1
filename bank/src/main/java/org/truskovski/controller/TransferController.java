package org.truskovski.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.truskovski.client.SbpClient;
import org.truskovski.model.transfer.dto.TransferDTO;
import org.truskovski.service.TransferService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
@Slf4j
public class TransferController {

    private final TransferService transferService;
    private final SbpClient  sbpClient;

    @PostMapping
    public ResponseEntity<String> transfer(
            @RequestBody TransferDTO request
    ) {

        if (request.amount().compareTo(BigDecimal.ZERO) <= 0){
            return ResponseEntity.badRequest().build();
        }

        transferService.performTransfer(
                request.senderPhone(),
                request.receiverPhone(),
                request.amount(),
                request.targetBank()
        );

        return ResponseEntity.ok("Transfer initiated");
    }
}