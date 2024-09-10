package study.surviveoutsidethejunglespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SurviveOutsideTheJungleSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurviveOutsideTheJungleSpringApplication.class, args);
	}

}
