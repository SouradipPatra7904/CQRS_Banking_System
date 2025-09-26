package souradippatra.CQRS_Banking_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CqrsBankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CqrsBankingSystemApplication.class, args);
	}

}
