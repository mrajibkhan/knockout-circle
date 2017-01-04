package com.games.circular.models;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by rajib.khan on 1/4/17.
 */
@Component
public class GameResult {

    protected Map<Integer, Integer> result = new HashMap<Integer, Integer>();

    public void add (Integer position, Player player) {
        this.result.put(position, player.getId());
    }

    public Optional<Integer> getAtPosition(Integer positionKey) {
        return (result.containsKey(positionKey))? Optional.of(result.get(positionKey)) : Optional.empty();
    }

    @Override
    public String toString() {
        StringBuilder resultBuilder = new StringBuilder();
        result.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByKey())
                .forEach((e) -> resultBuilder.append(e.getKey()).append(" - player#").append(e.getValue()).append("\n"));

        return resultBuilder.toString();
    }
}
