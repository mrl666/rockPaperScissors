package com.paperscissorsrock.model;

import java.util.Arrays;
import java.util.Optional;

public enum Choices {

	ROCK, PAPER, SCISSORS;
	
    public static Optional<Choices> getIfPresent(String str) {
        return Arrays.stream(Choices.values())
                     .filter(alphabet -> str.equals(alphabet.name()))
                     .findFirst();
    }
}
