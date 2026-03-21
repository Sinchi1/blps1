package org.truskovski.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.truskovski.model.user.User;
import org.truskovski.model.user.repository.UserRepository;
import org.truskovski.security.PasswordUtil;
import org.truskovski.service.OutboxService;
import org.truskovski.service.addition.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final OutboxService outboxService;
    private final JwtService jwtService;

    public String login(String email, String rawPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        String hash = PasswordUtil.hashPassword(
                rawPassword,
                user.getSalt()
        );

        if (!user.getEnc_pass().equals(hash))
            throw new RuntimeException("Bad pass");

        return jwtService.generate(email);
    }
}