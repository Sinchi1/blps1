package org.truskovski.model.bankparticipant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "banks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String bankCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String baseUrl;

    private boolean active;
}
