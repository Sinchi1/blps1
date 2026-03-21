package org.truskovski.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.truskovski.model.account.Account;
import org.truskovski.model.account.repository.AccountRepository;
import org.truskovski.model.enums.AccountStatus;
import org.truskovski.model.user.User;
import org.truskovski.model.user.repository.UserRepository;
import org.truskovski.security.PasswordUtil;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void register(String email,
                         String rawPassword,
                         String phone,
                         String fullName) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(rawPassword, salt);

        Account account = Account.builder()
                .balance(BigDecimal.ZERO)
                .cardNumber(generateCardNumber())
                .status(AccountStatus.ACTIVE)
                .phone(phone)
                .fullName(fullName)
                .build();

        accountRepository.save(account);

        User user = User.builder()
                .email(email)
                .enc_pass(hash)
                .salt(salt)
                .account(account)
                .build();

        userRepository.save(user);
    }

    private String generateCardNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
}