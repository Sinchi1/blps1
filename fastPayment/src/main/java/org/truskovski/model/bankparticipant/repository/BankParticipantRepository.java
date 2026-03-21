package org.truskovski.model.bankparticipant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.truskovski.model.bankparticipant.BankParticipant;

import java.util.Optional;

public interface BankParticipantRepository
        extends JpaRepository<BankParticipant, Long> {

    Optional<BankParticipant> findByBankCode(String code);
}