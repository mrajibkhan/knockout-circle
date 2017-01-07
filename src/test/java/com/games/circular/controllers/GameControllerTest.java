package com.games.circular.controllers;

import com.games.circular.exceptions.InsufficientPlayersException;
import com.games.circular.exceptions.InvalidCounterException;
import com.games.circular.models.GameResult;
import com.games.circular.models.KnockoutGame;
import com.games.circular.services.KnockoutService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by rajib.khan on 1/8/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    GameController gameController;

    @Mock
    KnockoutService knockoutService;

    GameResult gameResult;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        gameResult = new GameResult();
    }

    @Test
    public void playGameShouldCallKnockoutService() throws Exception {
        Mockito.doReturn(gameResult).when(knockoutService).execute(any(KnockoutGame.class));
        GameResult result = gameController.playGame();
        assertThat("should return game result", result, is(gameResult));
        verify(knockoutService, times(1)).execute(any(KnockoutGame.class));
    }

    @Test
    public void playGameShouldThrowErrorWhenInsufficientPlayers() throws Exception {
        Mockito.doThrow(new InsufficientPlayersException("test error")).when(knockoutService).execute(any(KnockoutGame.class));
        thrown.expect(InsufficientPlayersException.class);
        thrown.expectMessage("test error");
        gameController.playGame();
    }

    @Test
    public void playGameShouldThrowErrorWhenInvalid() throws Exception {
        Mockito.doThrow(new InvalidCounterException("test counter error")).when(knockoutService).execute(any(KnockoutGame.class));
        thrown.expect(InvalidCounterException.class);
        thrown.expectMessage("test counter error");
        gameController.playGame();
    }

    @Test
    public void getNumberOfPlayersShouldReturn0AsDefault() throws Exception {
        assertThat("should return 0 when default", gameController.getNumberOfPlayers(), is(0));
    }

    @Test
    public void setNumberOfPlayers() throws Exception {
        gameController.setNumberOfPlayers(10);
        assertThat("should return 10 when set", gameController.numberOfPlayers, is(10));
    }

    @Test
    public void getCounterShouldReturn0AsDefault() throws Exception {
        assertThat("should return 0 when default", gameController.getCounter(), is(0));
    }

    @Test
    public void getCounterShouldReturnValueAsSet() throws Exception {
        gameController.counter = 10;
        assertThat("should return 10 ", gameController.getCounter(), is(10));
    }

    @Test
    public void setCounter() throws Exception {
        gameController.setCounter(10);
        assertThat("should return 10 as set", gameController.counter, is(10));
    }

}