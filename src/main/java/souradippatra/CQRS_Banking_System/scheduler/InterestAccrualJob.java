package souradippatra.CQRS_Banking_System.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InterestAccrualJob {

    @Scheduled(cron = "0 0 0 * * ?") // daily at midnight
    public void accrueInterest() {
        System.out.println("Daily interest accrual executed");
        // TODO: Add logic to calculate interest and send Kafka events
    }
}
