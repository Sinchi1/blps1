package org.truskovski.model.register.dto;

public record RegisterDTO(String email,
                          String password,
                          String phone,
                          String fullName) {
}
