package com.games.circular;

import com.games.circular.controllers.GameController;
import com.games.circular.exceptions.InsufficientPlayersException;
import com.games.circular.exceptions.InvalidCounterException;
import com.games.circular.models.GameResult;
import com.games.circular.services.UserInteractionService;
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

	Scanner scanner;

	@Override
	public void run(String... args) {

		controller.playGame();
	}

	public static void main(String[] args) {
		SpringApplication.run(KnockoutApplication.class, args);
	}
}
