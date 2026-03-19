package org.truskovski.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.truskovski.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
