package org.truskovski.model.user.dto;

import org.truskovski.model.account.Account;

public record UserDTO(String email, String enc_pass, String salt, Account account) {
}
