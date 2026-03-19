package org.truskovski.model.account;

import jakarta.persistence.*;
import lombok.*;
import org.truskovski.model.enums.AccountStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false)
    private String phone;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void createSetTime(){
        this.createdAt = LocalDateTime.now();
    }

}