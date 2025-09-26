package souradippatra.CQRS_Banking_System.command.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import souradippatra.CQRS_Banking_System.command.dto.CreateTransactionCommand;
import souradippatra.CQRS_Banking_System.command.service.TransactionCommandHandler;

@RestController
@RequestMapping("/api/commands/transactions")
public class TransactionCommandController {

    private final TransactionCommandHandler handler;

    public TransactionCommandController(TransactionCommandHandler handler) {
        this.handler = handler;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionCommand command) {
        handler.handleCreateTransaction(command.accountId(), command.type(), command.amount());
        return ResponseEntity.ok("Transaction command sent successfully!");
    }
}
