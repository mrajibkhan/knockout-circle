package com.games.circular.services;

import com.games.circular.exceptions.InsufficientPlayersException;
import com.games.circular.exceptions.InvalidCounterException;
import com.games.circular.models.GameResult;
import com.games.circular.models.KnockoutGame;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by rajib.khan on 1/4/17.
 */
public class KnockoutServiceTest {

    KnockoutService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public OutputCapture capture = new OutputCapture();

    @Before
    public void setUp() {
        this.service = new KnockoutService();
    }

    @Test
    public void executeTestWithPositiveNumberOfPlayersAndCounter() throws Exception {
        KnockoutGame game = new KnockoutGame(3, 3);
        GameResult result = service.execute(game);
        assertThat(result.getAtPosition(3).get(), is(3));
        assertThat(result.getAtPosition(2).get(), is(1));
        assertThat(result.getAtPosition(1).get(), is(2));
    }

    @Test
    public void executeTestWithSinglePlayerAndPositiveCounter() throws Exception {
        KnockoutGame game = new KnockoutGame(1, 3);
        GameResult result = service.execute(game);
        assertThat(result.getAtPosition(1).get(), is(1));
        assertThat(capture.toString(), containsString("Total loop = 0"));
    }

    @Test
    public void executeReturnsEmptyResultWhenNoPlayers() throws Exception {
        KnockoutGame game = new KnockoutGame(0, 3);
        thrown.expect(InsufficientPlayersException.class);
        service.execute(game);
    }

    @Test
    public void executeReturnsEmptyResultWhenNumberOfPlayersIsNegative() throws Exception {
        KnockoutGame game = new KnockoutGame(-20, 3);
        thrown.expect(InsufficientPlayersException.class);
        service.execute(game);
    }

    @Test
    public void shouldThrowInvalidCounterExceptionWhenCounterIs0() throws Exception {
        KnockoutGame game = new KnockoutGame(3, 0);
        thrown.expect(InvalidCounterException.class);
        service.execute(game);
    }

    @Test
    public void shouldThrowInvalidCounterExceptionWhenCounterIsNegative() throws Exception {
        KnockoutGame game = new KnockoutGame(3, -5);
        thrown.expect(InvalidCounterException.class);
        service.execute(game);
    }

}