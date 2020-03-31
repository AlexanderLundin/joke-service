package com.example.joke.services;

import com.example.joke.entities.Joke;
import com.example.joke.entities.JokeType;
import com.example.joke.repositories.JokeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class JokeServiceTest {


    @Autowired
    private JokeService jokeService;


    @Autowired
    private JokeDao jokeDao;

    private Joke joke1;
    private Joke joke2;
    private Joke joke3;
    private String jokeContent;
    private JokeType jokeType;

    @BeforeEach
    public void setup() {
        //Setup
        jokeContent = "Most interesting man in the world: " +
                "\"I don't always connect to the database for testing...." +
                "but when I do, I drop the table afterwards.\" ;) ";
        jokeType = JokeType.TECHNOLOGY;
        joke1 = new Joke(jokeType, jokeContent);
        jokeContent = "Most interesting man in the world: " +
                "\"I don't always run the regression tests between releases...." +
                "but when I do, I don't refactor the failures.\" ;) ";
        jokeType = JokeType.TECHNOLOGY;
        joke2 = new Joke(jokeType, jokeContent);
        jokeContent = "Most interesting man in the world: " +
                "\"I don't always have bugs in my code...." +
                "but when I do, it shuts our production environment down.\" ;) ";
        jokeType = JokeType.TECHNOLOGY;
        joke3 = new Joke(jokeType, jokeContent);

    }


    //CREATE


    @Test
    public void save_validJoke_returnsJoke() {
        //Setup
        Joke actual = jokeService.save(joke1);
        //Exercise

        //Assert
        assertEquals(joke1.hashCode(), actual.hashCode());
        //Teardown
    }

}