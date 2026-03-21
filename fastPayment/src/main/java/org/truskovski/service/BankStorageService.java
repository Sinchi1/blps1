package org.truskovski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.truskovski.model.bankparticipant.BankParticipant;
import org.truskovski.model.bankparticipant.repository.BankParticipantRepository;
import org.truskovski.model.phonebanklink.repository.PhoneBankLinkRepository;

@Service
@RequiredArgsConstructor
public class BankStorageService {

    private final PhoneBankLinkRepository linkRepo;
    private final BankParticipantRepository bankRepo;

    public BankParticipant resolveBank(String targetBank) {

        boolean exists = linkRepo.existsByBankCode(targetBank);

        if (!exists) {
            throw new RuntimeException(
                    "Receiver has no account in bank " + targetBank
            );
        }

        return bankRepo.findByBankCode(targetBank)
                .orElseThrow(() ->
                        new RuntimeException("Bank not registered"));
    }
}