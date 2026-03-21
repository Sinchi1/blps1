package org.truskovski.model.phonebanklink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.truskovski.model.phonebanklink.PhoneBankLink;

import java.util.List;

public interface PhoneBankLinkRepository extends JpaRepository<PhoneBankLink, Long> {

    List<PhoneBankLink> findByPhone(String phone);

    boolean existsByPhoneAndBankCode(
            String phone,
            String bankCode
    );
}