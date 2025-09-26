package souradippatra.CQRS_Banking_System.event;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionEvent(
        String transactionId,
        String accountId,
        BigDecimal amount,
        String type, // CREDIT or DEBIT
        Instant timestamp
) {}
