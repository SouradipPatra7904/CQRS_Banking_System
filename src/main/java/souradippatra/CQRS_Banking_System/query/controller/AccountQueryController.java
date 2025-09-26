package souradippatra.CQRS_Banking_System.query.controller;

import org.springframework.web.bind.annotation.*;
import souradippatra.CQRS_Banking_System.query.model.AccountSummary;
import souradippatra.CQRS_Banking_System.query.repository.AccountSummaryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/queries/accounts")
public class AccountQueryController {

    private final AccountSummaryRepository repository;

    public AccountQueryController(AccountSummaryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<AccountSummary> getAllAccounts() {
        return repository.findAll();
    }

    @GetMapping("/{accountId}")
    public AccountSummary getAccount(@PathVariable String accountId) {
        return repository.findById(accountId).orElse(null);
    }
}
