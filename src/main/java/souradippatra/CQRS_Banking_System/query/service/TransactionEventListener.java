package souradippatra.CQRS_Banking_System.query.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import souradippatra.CQRS_Banking_System.event.TransactionEvent;
import souradippatra.CQRS_Banking_System.query.model.AccountSummary;
import souradippatra.CQRS_Banking_System.query.repository.AccountSummaryRepository;

import java.math.BigDecimal;

// Simple Bank Transaction Event Listener for updating AccountSummary
@Service
public class TransactionEventListener {

    private final AccountSummaryRepository repository;

    public TransactionEventListener(AccountSummaryRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "transactions", groupId = "cqrs_bank_group")
    @Transactional
    public void onTransactionEvent(TransactionEvent event) {
        AccountSummary summary = repository.findById(event.accountId())
                .orElseGet(() -> new AccountSummary(event.accountId(), BigDecimal.ZERO));

        BigDecimal newBalance = summary.getBalance();
        if ("CREDIT".equalsIgnoreCase(event.type())) {
            newBalance = newBalance.add(event.amount());
        } else if ("DEBIT".equalsIgnoreCase(event.type())) {
            newBalance = newBalance.subtract(event.amount());
        }

        summary.setBalance(newBalance);
        repository.save(summary);
    }
}
