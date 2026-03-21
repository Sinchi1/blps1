package org.truskovski.model.transfer.dto;

import jakarta.persistence.*;
import lombok.*;
import org.truskovski.model.transfer.TransferStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderPhone;
    private String receiverPhone;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    private LocalDateTime createdAt;

    private String targetBank;

    @PrePersist
    void init() {
        createdAt = LocalDateTime.now();
        status = TransferStatus.INITIATED;
    }
}
