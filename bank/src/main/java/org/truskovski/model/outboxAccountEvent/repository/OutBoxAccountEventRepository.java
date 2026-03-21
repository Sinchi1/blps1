package org.truskovski.model.outboxAccountEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.truskovski.model.outboxAccountEvent.OutBoxAccountEvent;

@Repository
public interface OutBoxAccountEventRepository extends JpaRepository<OutBoxAccountEvent, Integer> {
}
