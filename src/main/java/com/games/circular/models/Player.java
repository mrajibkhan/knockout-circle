package com.games.circular.models;

/**
 * Created by rajib.khan on 1/2/17.
 */
public class Player {
    public Integer getId() {
        return id;
    }

    Integer id;
    public Player(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                '}';
    }
}
