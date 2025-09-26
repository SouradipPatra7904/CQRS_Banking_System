package souradippatra.CQRS_Banking_System.query.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class AccountSummary {

    @Id
    private String accountId;
    private BigDecimal balance;

    public AccountSummary() {}
    
    public AccountSummary(String accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
