package org.truskovski.model.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.truskovski.model.transfer.dto.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}