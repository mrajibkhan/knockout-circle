package com.games.circular;

import com.games.circular.controller.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@SpringBootApplication
public class KnockoutApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(KnockoutApplication.class);

	@Autowired
	GameController controller;

	@Override
	public void run(String... args) {
		if (controller.getNumberOfPlayers().equals("") && controller.getCounter().equals("")) {
			try (Scanner scanner = new Scanner(System.in)) {
				log.info("Enter number of KIDS (n)");
				controller.setNumberOfPlayers(scanner.nextLine());

				log.info("Enter value of counter (k)");
				controller.setCounter(scanner.nextLine());
			} catch (Exception ex) {
				log.error("" + ex.getMessage());
			}
		}

		log.info("Number of players = " + controller.getNumberOfPlayers() + ", value of k = " + controller.getCounter());
		log.info("Restart the application to play again");
	}

	public static void main(String[] args) {
		SpringApplication.run(KnockoutApplication.class, args);
	}
}
