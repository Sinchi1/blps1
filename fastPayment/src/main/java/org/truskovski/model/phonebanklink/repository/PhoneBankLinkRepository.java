package org.truskovski.model.phonebanklink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.truskovski.model.phonebanklink.PhoneBankLink;

public interface PhoneBankLinkRepository extends JpaRepository<PhoneBankLink, Long> {

    boolean existsByPhoneAndBankCode(
            String phone,
            String bankCode
    );

    boolean existsByBankCode(String bankCode);
}