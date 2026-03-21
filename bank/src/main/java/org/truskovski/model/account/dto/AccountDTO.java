package org.truskovski.model.account.dto;

import org.truskovski.model.enums.AccountStatus;

import java.math.BigDecimal;

public record AccountDTO(BigDecimal balance,
                         String cardNumber,
                         AccountStatus status,
                         String phone,
                         String fullName
) {
}
