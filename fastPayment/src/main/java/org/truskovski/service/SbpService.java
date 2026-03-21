package org.truskovski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.truskovski.client.BankClient;
import org.truskovski.model.transferRequest.dto.TransferRequestDTO;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SbpService {

    private final BankClient bankClient;

    public void processTransfer(TransferRequestDTO request) {
        Map<String, Object> body = Map.of(
                "phone", request.receiverPhone(),
                "amount", request.amount()
        );

        // Если банк вернёт 4xx/5xx → Feign бросит FeignException → транзакция в банке-отправителе откатится
        bankClient.deposit(body);
    }
}