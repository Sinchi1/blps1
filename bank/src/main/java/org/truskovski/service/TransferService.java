package org.truskovski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.truskovski.client.SbpClient;
import org.truskovski.model.account.Account;
import org.truskovski.model.transfer.TransferStatus;
import org.truskovski.model.transfer.dto.Transfer;
import org.truskovski.model.transfer.dto.TransferDTO;
import org.truskovski.model.transfer.repository.TransferRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountService accountService;
    private final TransferRepository transferRepository;
    private final OutboxService outboxService;
    private final SbpClient sbpClient;

    @Transactional
    public void performTransfer(String senderPhone,
                                String receiverPhone,
                                BigDecimal amount,
                                String targetBank) {

        if (senderPhone.equals(receiverPhone))
            throw new RuntimeException("Self transfer");

        TransferDTO transferDto = new TransferDTO(
                senderPhone,
                receiverPhone,
                amount,
                targetBank
        );

        Account sender = accountService.getByPhone(senderPhone);

        accountService.validateDebit(sender.getId(), amount);
        accountService.debit(sender.getId(), amount);

        Transfer transfer = Transfer.builder()
                .senderPhone(senderPhone)
                .receiverPhone(receiverPhone)
                .amount(amount)
                .targetBank(targetBank)
                .status(TransferStatus.DEBIT_DONE)
                .build();

        transferRepository.save(transfer);

        sbpClient.transfer(transferDto);

        outboxService.saveEvent(
                "TRANSFER_REQUESTED",
                transfer.getId().toString()
        );
    }
}