package com.games.circular.controllers;

import com.games.circular.exceptions.InsufficientPlayersException;
import com.games.circular.exceptions.InvalidCounterException;
import com.games.circular.models.GameResult;
import com.games.circular.models.KnockoutGame;
import com.games.circular.services.KnockoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by rajib.khan on 1/2/17.
 * Sets up the configuration for a game i.e.
 * number of kids / players (n) and the counter (k).
 */

@Component
public class GameController {

    private KnockoutService knockoutService;

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
    public GameResult playGame() throws InsufficientPlayersException, InvalidCounterException {
        return knockoutService.execute(new KnockoutGame(numberOfPlayers, counter));
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
