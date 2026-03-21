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

    @Column
    private String senderPhone;

    @Column
    private String receiverPhone;

    @Column
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String targetBank;

    @PrePersist
    void init() {
        createdAt = LocalDateTime.now();
        status = TransferStatus.INITIATED;
    }
}
