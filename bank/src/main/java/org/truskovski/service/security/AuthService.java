package org.truskovski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.truskovski.model.user.User;
import org.truskovski.model.user.repository.UserRepository;
import org.truskovski.security.PasswordUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final OutboxService outboxService;

    public void login(String email, String rawPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String hash = PasswordUtil.hashPassword(rawPassword, user.getSalt());

        if (!user.getEnc_pass().equals(hash)) {
            throw new RuntimeException("Invalid password");
        }

        outboxService.saveEvent("LOGIN_SUCCESS", email);
    }
}