package org.truskovski.model.phonebanklink;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phone_bank_links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneBankLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String bankCode;

    private boolean primaryAccount;
}