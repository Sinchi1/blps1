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
public class AccountService {

    private final AccountRepository accountRepository;
    private final OutboxService outboxService;

    public Account getByPhone(String phone) {
        return accountRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public void validateDebit(Long accountId, BigDecimal amount) {
        Account acc = get(accountId);

        if (acc.getStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException("Account not active");
        }

        if (acc.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
    }

    @Transactional
    public void debit(Long accountId, BigDecimal amount) {
        Account acc = get(accountId);

        acc.setBalance(acc.getBalance().subtract(amount));

        outboxService.saveEvent("DEBIT", acc.getId().toString());
    }

    public void validateCredit(Long accountId) {
        Account acc = get(accountId);

        if (acc.getStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException("Receiver blocked");
        }
    }

    @Transactional
    public void credit(Long accountId, BigDecimal amount) {
        Account acc = get(accountId);

        acc.setBalance(acc.getBalance().add(amount));

        outboxService.saveEvent("CREDIT", acc.getId().toString());
    }

    public Account get(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}