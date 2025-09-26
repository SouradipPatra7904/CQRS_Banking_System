package souradippatra.CQRS_Banking_System.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import souradippatra.CQRS_Banking_System.query.model.AccountSummary;

public interface AccountSummaryRepository extends JpaRepository<AccountSummary, String> {}
