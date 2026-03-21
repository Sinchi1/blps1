package org.truskovski.service.addition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.truskovski.model.outboxAccountEvent.OutBoxAccountEvent;
import org.truskovski.model.outboxAccountEvent.repository.OutBoxAccountEventRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxProcessor {

    private final OutBoxAccountEventRepository repository;

    @Scheduled(fixedDelay = 5000)
    public void process() {
        List<OutBoxAccountEvent> events = repository.findAll();

        for (OutBoxAccountEvent e : events) {
            if ("NEW".equals(e.getStatus())) {

                log.info("Send event: {} {}", e.getEventType(), e.getPayload());

                e.setStatus("SENT");
                repository.save(e);
            }
        }
    }
}