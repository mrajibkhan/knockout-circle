package com.games.circular.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rajib.khan on 1/4/17.
 */
public class GameResultTest {

    private GameResult gameResult;

    @Before
    public void setUp() {
        gameResult = new GameResult();
    }

    @Test
    public void shouldAddPlayerWithPosition() {
        gameResult.add(1, new Player(2));
       assertThat("should contain 1 entry", gameResult.result.entrySet().size(), is(1));
       assertThat(gameResult.result.get(1), is(2));
    }

    @Test
    public void shouldReturnPlayersCorrectPosition() {
        gameResult.add(1, new Player(2));
        gameResult.add(2, new Player(1));
        assertThat(gameResult.getAtPosition(1).get(), is(2));
        assertThat(gameResult.getAtPosition(2).get(), is(1));
    }

    @Test
    public void shouldReturnEmptyForNonExistingPosition() {
        assertThat(gameResult.getAtPosition(10), is(Optional.empty()));
    }

    @Test
    public void shouldReturnResultString() {
        String expectedResult = new StringBuilder().append("1 - player#2\n")
                .append("2 - player#3\n")
                .append("3 - player#1\n").toString();

        gameResult.add(2, new Player(3));
        gameResult.add(3, new Player(1));
        gameResult.add(1, new Player(2));

        String result = gameResult.toString();
        System.out.println(result);
        assertThat(expectedResult, is(result));
    }

}