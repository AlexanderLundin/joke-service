package com.example.joke.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JokeTest {

    @Test
    public void jokeConstructor_validInput_returnsJoke() {
        //Setup
        //Exercise
        Joke joke = new Joke();
        //Assert
        assertNotNull(joke);
        //Teardown
    }

}