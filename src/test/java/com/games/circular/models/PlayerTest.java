package com.games.circular.models;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by rajib.khan on 1/4/17.
 */
public class PlayerTest {

    @Test
    public void constructorShouldSetId() {
        Player testPlayer = new Player(1);
        assertThat("id should be 1", testPlayer.id, is(1));
    }

}