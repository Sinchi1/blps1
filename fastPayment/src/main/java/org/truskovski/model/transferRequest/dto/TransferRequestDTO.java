package org.truskovski.model.transferRequest.dto;

import java.math.BigDecimal;

public record TransferRequestDTO(
        String senderPhone,
        String receiverPhone,
        BigDecimal amount,
        String targetBank
) {}