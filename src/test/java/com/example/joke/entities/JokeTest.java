package com.example.joke.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JokeTest {

    @Test
    public void jokeDefaultConstructor_returnsJoke() {
        //Setup
        //Exercise
        Joke joke = new Joke();
        //Assert
        assertNotNull(joke);
        //Teardown
    }

    @Test
    public void jokeConstructor_validInput_returnsJoke() {
        //Setup
        //Exercise
        Joke joke = new Joke(JokeType.TECHNOLOGY, "Most interesting man in the world: \"I don't always test my code, but when I do test....I test in production\" ;) ");
        //Assert
        assertNotNull(joke);
        //Teardown
    }

}