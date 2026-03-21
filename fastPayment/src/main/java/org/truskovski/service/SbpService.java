package org.truskovski.service;

import feign.Feign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.truskovski.client.BankClient;
import org.truskovski.model.bankparticipant.BankParticipant;
import org.truskovski.model.transferRequest.dto.TransferRequestDTO;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SbpService {

    private final BankStorageService storage;
    private final Feign.Builder feign;

    public void processTransfer(TransferRequestDTO req) {

        BankParticipant bank =
                storage.resolveBank(
                        req.receiverPhone(),
                        req.targetBank()
                );

        BankClient client =
                feign.target(
                        BankClient.class,
                        bank.getBaseUrl()
                );

        client.deposit(
                Map.of(
                        "phone", req.receiverPhone(),
                        "amount", req.amount()
                )
        );
    }
}