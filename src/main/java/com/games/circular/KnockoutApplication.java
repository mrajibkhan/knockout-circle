package com.games.circular;

import com.games.circular.controllers.GameController;
import com.games.circular.models.GameResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class KnockoutApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(KnockoutApplication.class);

	@Autowired
	GameController controller;

	@Override
	public void run(String... args) {
		readUserInput();
		log.info("Number of players = " + controller.getNumberOfPlayers() + ", value of k = " + controller.getCounter());
		try {
			GameResult result = controller.playGame();
			log.info("Game Result: \n" + result);
		} catch (Exception ex) {
			log.error("Error: " + ex.getMessage());
		}
		log.info("Restart the application to play again");
	}

	protected void readUserInput () {
		if (controller.getNumberOfPlayers() == 0 && controller.getCounter() == 0) {
			try (Scanner scanner = new Scanner(System.in)) {
				log.info("Enter number of KIDS (n)");
				controller.setNumberOfPlayers(scanner.nextInt());

				log.info("Enter value of counter (k)");
				controller.setCounter(scanner.nextInt());

			} catch (InputMismatchException ex) {
				log.error("Error: Input Mismatch. Please provide a valid integer value");
			}
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(KnockoutApplication.class, args);
	}
}
