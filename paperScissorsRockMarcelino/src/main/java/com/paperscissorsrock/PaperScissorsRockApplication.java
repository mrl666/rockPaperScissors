package com.paperscissorsrock;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaperScissorsRockApplication {
	
	static Logger log = Logger.getLogger(PaperScissorsRockApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(PaperScissorsRockApplication.class, args);
		log.info("Running PaperScissorsRockApplication.");
	}
}
