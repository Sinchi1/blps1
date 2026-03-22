package org.truskovski.service;

import feign.Feign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.truskovski.client.BankClient;
import org.truskovski.model.DepositDTO;
import org.truskovski.model.bankparticipant.BankParticipant;
import org.truskovski.model.bankparticipant.repository.BankParticipantRepository;
import org.truskovski.model.transferRequest.dto.TransferRequestDTO;


@RequiredArgsConstructor
@Slf4j
@Service
public class SbpService {

    private final BankStorageService storage;
    private final Feign.Builder feign;
    private final BankClient ourbankClient;
    private final BankParticipantRepository repository;

    public ResponseEntity<String> processTransfer(@RequestBody TransferRequestDTO req) {

        log.info("Processing transfer request");
        log.info(req.toString());

        BankParticipant bank = storage.resolveBank(req.targetBank());

        log.info("Bank participant found: {}", bank.getBaseUrl());

        BankClient targetBankClient = feign.target(BankClient.class, repository.findByBankCode(req.targetBank()).get().getBaseUrl());

        try {
            targetBankClient.deposit(
                    new DepositDTO(req.receiverPhone(), req.amount()));
        } catch (Exception depositFailed) {

            ourbankClient.deposit(new DepositDTO(req.senderPhone(), req.amount()));

            log.error("depositFailed", depositFailed);

            throw new RuntimeException("Transfer to target bank failed", depositFailed);
        }
    return ResponseEntity.ok("Transfer successful");
    }

}