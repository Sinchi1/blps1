package org.truskovski.model.user;

import jakarta.persistence.*;
import lombok.*;
import org.truskovski.model.account.Account;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "enc_pass", nullable = false)
    private String enc_pass;

    @Column(nullable = false)
    private String salt;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    private Account account;
}