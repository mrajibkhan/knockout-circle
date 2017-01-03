package com.games.circular;

import com.games.circular.model.Player;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by rajib.khan on 12/22/16.
 */
public class GuavaIterablesTest {

    public static void main(String[] args){
        FluentIterable<Player> players = getPlayers(6);
        FluentIterable<Player> cyclicPlayers = players.cycle();
        Iterator<Player> iterator = cyclicPlayers.iterator();

        Player out;
        while (players.size() > 1) {
            System.out.println("Size: " + players.size());
            out = removeAtIndex(iterator, 9);
            System.out.println("OUT: " + out);
        }

        System.out.println("Winner is: " + players.toString());


    }

    private static FluentIterable<Integer> fluent(Integer... elements) {
        return FluentIterable.from(Lists.newArrayList(elements));
    }

    private static FluentIterable<Player> getPlayers (int totalPlayers) {
        Stream<Player> playerStream = IntStream.rangeClosed(1, totalPlayers).mapToObj(i -> new Player(i));
        FluentIterable<Player> playerIt = FluentIterable.from(playerStream.collect(Collectors.toList()));

        return playerIt;
    }

    private static Player removeAtIndex (Iterator<Player> iterator, int index) {
        Player rem = null;


        for(int i=0; i < index; i++) {
            System.out.println(iterator.next());

        }

        rem = iterator.next();
        iterator.remove();

        return rem;

    }


}
