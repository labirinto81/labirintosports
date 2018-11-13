package ch.labirintosports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class LabirintosportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabirintosportsApplication.class, args);
	}
}
