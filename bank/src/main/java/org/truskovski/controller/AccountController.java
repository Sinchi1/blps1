package org.truskovski.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.truskovski.model.account.Account;
import org.truskovski.service.AccountService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping("/{id}")
    public Account get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/by-phone")
    public Account getByPhone(@RequestParam String phone) {
        return service.getByPhone(phone);
    }

    @PostMapping("/validate-debit")
    public void validateDebit(@RequestParam Long accountId,
                              @RequestParam BigDecimal amount) {
        service.validateDebit(accountId, amount);
    }

    @PostMapping("/debit")
    public void debit(@RequestParam Long accountId,
                      @RequestParam BigDecimal amount) {
        service.debit(accountId, amount);
    }

    @PostMapping("/validate-credit")
    public void validateCredit(@RequestParam Long accountId) {
        service.validateCredit(accountId);
    }

    @PostMapping("/credit")
    public void credit(@RequestParam Long accountId,
                       @RequestParam BigDecimal amount) {
        service.credit(accountId, amount);
    }
}