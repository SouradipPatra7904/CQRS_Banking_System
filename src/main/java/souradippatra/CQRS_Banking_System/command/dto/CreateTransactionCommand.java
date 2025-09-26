package souradippatra.CQRS_Banking_System.command.dto;

import java.math.BigDecimal;

public record CreateTransactionCommand(
        String accountId,
        BigDecimal amount,
        String type // CREDIT / DEBIT
) {}
