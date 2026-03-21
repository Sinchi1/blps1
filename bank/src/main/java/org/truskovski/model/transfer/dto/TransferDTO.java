package org.truskovski.model.transfer.dto;

import java.math.BigDecimal;

public record TransferDTO(
        String senderPhone,
        String receiverPhone,
        BigDecimal amount
) {}