package org.truskovski.model.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.truskovski.model.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
}
