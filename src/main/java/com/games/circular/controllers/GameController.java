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
 */

@Component
public class GameController {

    @Autowired
    private KnockoutService service;

    private int numberOfPlayers;
    private int counter;

    public GameResult playGame() throws InsufficientPlayersException, InvalidCounterException {
        return service.execute(new KnockoutGame(numberOfPlayers, counter));
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
