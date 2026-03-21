package org.truskovski.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.truskovski.model.outboxAccountEvent.OutBoxAccountEvent;
import org.truskovski.model.outboxAccountEvent.repository.OutBoxAccountEventRepository;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutBoxAccountEventRepository repository;

    public void saveEvent(String type, String payload) {
        var event = OutBoxAccountEvent.builder()
                .eventType(type)
                .payload(payload)
                .build();

        repository.save(event);
    }
}