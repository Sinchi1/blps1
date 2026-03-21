package org.truskovski.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "bank-service", url = "http://localhost:8080")
public interface BankClient {

    @PostMapping("/accounts/deposit")
    void deposit(@RequestBody Map<String, Object> request);
}