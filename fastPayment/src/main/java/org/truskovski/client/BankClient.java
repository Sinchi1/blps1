package org.truskovski.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.truskovski.model.DepositDTO;

import java.util.Map;

@FeignClient(name = "bank-service", url = "http://localhost:54322")
public interface BankClient {

    @PostMapping("/accounts/deposit")
    void deposit(@RequestBody DepositDTO request);
}