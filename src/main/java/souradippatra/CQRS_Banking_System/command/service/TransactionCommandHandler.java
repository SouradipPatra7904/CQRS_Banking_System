package souradippatra.CQRS_Banking_System.command.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import souradippatra.CQRS_Banking_System.event.TransactionEvent;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class TransactionCommandHandler {

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public TransactionCommandHandler(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void handleCreateTransaction(String accountId, String type, BigDecimal amount) {
        TransactionEvent event = new TransactionEvent(
                UUID.randomUUID().toString(),
                accountId,
                amount,
                type,
                Instant.now()
        );
        kafkaTemplate.send("transactions", event.transactionId(), event);
    }
}
