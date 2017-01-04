package com.games.circular.services;

import com.games.circular.exceptions.InsufficientPlayersException;
import com.games.circular.exceptions.InvalidCounterException;
import com.games.circular.models.GameResult;
import com.games.circular.models.KnockoutGame;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rajib.khan on 1/4/17.
 */
public class KnockoutServiceTest {

    KnockoutService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        this.service = new KnockoutService();
    }

    @Test
    public void executeReturnsEmptyResultWhenNoPlayers() throws Exception {
        KnockoutGame game = new KnockoutGame(0, 3);
        thrown.expect(InsufficientPlayersException.class);
        service.execute(game);

    }

    @Test
    public void executeTest() throws Exception {
        KnockoutGame game = new KnockoutGame(3, 3);
        GameResult result = service.execute(game);
        assertThat(result.getAtPosition(3).get(), is(3));
        assertThat(result.getAtPosition(2).get(), is(1));
        assertThat(result.getAtPosition(1).get(), is(2));
    }

    @Test
    public void shouldThrowInvalidCounterExceptionWhenCounterIs0() throws Exception {
        KnockoutGame game = new KnockoutGame(3, 0);
        thrown.expect(InvalidCounterException.class);
        service.execute(game);
    }

}