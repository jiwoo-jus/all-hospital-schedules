package jiwjus.AllHospitalSchedules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AllHospitalSchedulesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllHospitalSchedulesApplication.class, args);
	}

}
