package org.truskovski.model.deposit.dto;

import java.math.BigDecimal;

public record DepositDTO(String phone,
                         BigDecimal amount) {
}