package org.truskovski.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.truskovski.model.account.Account;
import org.truskovski.model.deposit.dto.DepositDTO;
import org.truskovski.service.AccountService;
import org.truskovski.service.DepositService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final DepositService depositService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.get(id));
    }

    @GetMapping("/by-phone")
    public ResponseEntity<Account> getByPhone(@RequestParam String phone) {
        return ResponseEntity.ok(accountService.getByPhone(phone));
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositDTO request) {
        if (request.amount().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().build();
        }

        depositService.deposit(request.phone(), request.amount());

        return ResponseEntity.ok("Deposit successful");
    }

    @PostMapping("/debit")
    public ResponseEntity<String> debit(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        accountService.debit(accountId, amount);
        return ResponseEntity.ok("Debited");
    }
}