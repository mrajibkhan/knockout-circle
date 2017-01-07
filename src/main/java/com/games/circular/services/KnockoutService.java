package com.games.circular.services;

import com.games.circular.exceptions.InsufficientPlayersException;
import com.games.circular.exceptions.InvalidCounterException;
import com.games.circular.models.GameResult;
import com.games.circular.models.KnockoutGame;
import com.games.circular.models.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * Created by rajib.khan on 1/4/17.
 *
 */
@Component
public class KnockoutService {

    Logger log = LoggerFactory.getLogger(KnockoutService.class);

    /**
     * Service method to run the game and provide the result. Iterates through the cyclic iterator
     * of players and removes players based on the counter value in the game.
     * @param game instance of the {@code KnockoutGame}
     * @return {@code GameResult}
     * @throws InsufficientPlayersException when execution fails due to the number of players e.g. 0 or negative
     * @throws InvalidCounterException when execution fails due to the value of counter e.g. 0 or negative
     */
    public GameResult execute(KnockoutGame game) throws InsufficientPlayersException, InvalidCounterException {
        GameResult result = new GameResult();
        Player knockedOutPlayer;

        List<Player> players = game.getPlayerList();
        if(players.size() < 1) {
           throw new InsufficientPlayersException("No players found.");
        } else if (game.getCounter() < 1) {
           throw new InvalidCounterException("Counter must be greater than 0");
        }
        int loopCount = 0;

        Iterator<Player> cyclicIterator = game.getCyclicPlayers().iterator();

        while (game.getPlayerList().size() > 1) {
            loopCount++;
            knockedOutPlayer = getAtIndex(cyclicIterator, game.getCounter());
            result.add(game.getPlayers().size(), knockedOutPlayer);
            cyclicIterator.remove();
            log.info("Iteration " + loopCount + " - OUT: " + knockedOutPlayer);
        }

        // remaining player is the winner
        result.add(players.size(), players.get(0));
        log.info("Winner: " + players.get(0));
        log.info("Total loop = " + loopCount);

        return result;
    }

    /**
     * Iterates starting from the current pointer position of the iterator
     * and returns the element ({@code Player}) at the index
     * @param iterator
     * @param index
     * @return
     */
    protected Player getAtIndex (Iterator<Player> iterator, int index) {
        for(int i=0; i < index -1; i++) {
            iterator.next();
        }

        return iterator.next();
    }




}
