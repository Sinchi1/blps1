package org.truskovski.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.truskovski.model.transfer.dto.TransferDTO;

@FeignClient(name = "sbp-service", url = "http://localhost:8081")
public interface SbpClient {

    @PostMapping("/sbp/transfer")
    void transfer(@RequestBody TransferDTO request);
}