package com.games.circular.controllers;

import com.games.circular.exceptions.InsufficientPlayersException;
import com.games.circular.exceptions.InvalidCounterException;
import com.games.circular.models.GameResult;
import com.games.circular.models.KnockoutGame;
import com.games.circular.services.KnockoutService;
import com.games.circular.services.UserInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

/**
 * Created by rajib.khan on 1/2/17.
 * Sets up the configuration for a game i.e.
 * number of kids / players (n) and the counter (k).
 */

@Component
public class GameController {

    Logger log = LoggerFactory.getLogger(GameController.class);

    private KnockoutService knockoutService;
    private UserInteractionService userInteractionService;

    @Autowired
    public void setService(KnockoutService knockoutService) {
        this.knockoutService = knockoutService;
    }

    protected int numberOfPlayers;
    protected int counter;

    /**
     * Runs the game and returns the result
     * @return GameResult
     * @throws InsufficientPlayersException when execution fails due to the number of players e.g. 0
     * @throws InvalidCounterException when execution fails due to the value of counter
     */
    public GameResult playGame() { //throws InsufficientPlayersException, InvalidCounterException {
        // show welcome message at the beginning of the application
        userInteractionService.showWelcomeMessage();
        // show help message to show accepted commands
        userInteractionService.showHelpMessage();
        readUserInput();

        GameResult result = null;
        log.info("Number of players = " + getNumberOfPlayers() + ", value of k = " + getCounter());
        try {
            result = knockoutService.execute(new KnockoutGame(numberOfPlayers, counter));
            log.info("Game Result: \n" + result);
//            log.info("Enter continue to play another game or anything to quit");
//            if(userInteractionService.readUserInput().equalsIgnoreCase("continue")) {
//                setNumberOfPlayers(0);
//                setCounter(0);
//                readUserInput();
//            } else {
//                log.warn("Exiting");
//            }
        } catch (Exception ex) {
            log.error("Error: " + ex.getMessage());
        }
        log.info("Restart the application to play again");

        return result;
    }

    protected void readUserInput () {
        if (getNumberOfPlayers() < 1 || getCounter() < 1) {
            try {

                if (getNumberOfPlayers() < 1) {
                    log.info("Enter number of players / kids (n)");
                    setNumberOfPlayers(userInteractionService.readUserInputInt());
                }

                if(getCounter() < 1) {
                    log.info("Enter value of counter (k)");
                    setCounter(userInteractionService.readUserInputInt());
                }

            } catch (InputMismatchException ex) {
                log.error("Error: Input Mismatch. Please provide a valid integer value");
            }

        }
    }

    @Autowired
    public void setUserInteractionService(UserInteractionService userInteractionService) {
        this.userInteractionService = userInteractionService;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Value("${n:0}")
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getCounter() {
        return counter;
    }

    @Value("${k:0}")
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
