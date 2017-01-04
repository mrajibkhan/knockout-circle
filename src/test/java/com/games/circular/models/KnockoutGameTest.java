package com.games.circular.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rajib.khan on 1/4/17.
 */
public class KnockoutGameTest {

    private KnockoutGame game;

    @Before
    public void setUp() {
        this.game = new KnockoutGame(5, 3);
    }

    @Test
    public void constructorShouldSet5Players () {
        assertThat("size should return 5", game.getPlayers().size(), is(5));
    }

    @Test
    public void constructorShouldSetCounterAs3 () {
        assertThat("should return counter as 3 ", game.getCounter(), is(3));
    }

    @Test
    public void constructorShouldSetCyclicIterable() {
        assertThat("should return 1st player as 6th node", game.getCyclicPlayers().get(5).getId(), is(1));
    }

}