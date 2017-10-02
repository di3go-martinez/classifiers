package org.bioplat.classifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClassifiersApplication {


	public static void main(String[] args) {
		SpringApplication.run(ClassifiersApplication.class, args);
	}

	private static final Logger logger = LoggerFactory.getLogger(ClassifiersApplication.class);
}
