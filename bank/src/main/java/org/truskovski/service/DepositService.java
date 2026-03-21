package org.truskovski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.truskovski.model.account.Account;
import org.truskovski.model.account.repository.AccountRepository;
import org.truskovski.model.enums.AccountStatus;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DepositService {

    private final AccountRepository accountRepository;
    private final OutboxService outboxService;

    @Transactional
    public void deposit(String phone, BigDecimal amount) {

        Account account = accountRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException("Account is blocked");
        }

        account.setBalance(account.getBalance().add(amount));

        outboxService.saveEvent("DEPOSIT_SUCCESS", phone);
    }
}