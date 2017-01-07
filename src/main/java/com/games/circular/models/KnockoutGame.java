package com.games.circular.models;

import com.google.common.collect.FluentIterable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by rajib.khan on 1/4/17.
 */
public class KnockoutGame {

    public List<Player> playerList;
    private FluentIterable<Player> iterablePlayers;

    // Circular Iterable of participating players with connected head and tail nodes
    private FluentIterable<Player> cyclicPlayers;
    private int counter;

    /**
     * Instantiates KnockoutGame by creating Iterables (FluentIterable), Cyclic Iterable
     * and sets the counter
     * @param totalPlayers
     * @param counter
     */
    public KnockoutGame(int totalPlayers, int counter) {
        Stream<Player> playerStream = IntStream.rangeClosed(1, totalPlayers).mapToObj(i -> new Player(i));

        this.playerList = IntStream.rangeClosed(1, totalPlayers).mapToObj(i -> new Player(i)).collect(Collectors.toList());
        this.iterablePlayers = FluentIterable.from(playerList);
        this.cyclicPlayers = iterablePlayers.cycle();
        this.counter = counter;
    }

    public FluentIterable<Player> getPlayers() {
        return iterablePlayers;
    }

    public FluentIterable<Player> getCyclicPlayers() {
        return cyclicPlayers;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public FluentIterable<Player> getIterablePlayers() {
        return iterablePlayers;
    }

    public int getCounter() {
        return counter;
    }
}
