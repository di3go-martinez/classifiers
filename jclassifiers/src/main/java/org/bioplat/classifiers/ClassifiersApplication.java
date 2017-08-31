package org.bioplat.classifiers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClassifiersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassifiersApplication.class, args);
	}
}
