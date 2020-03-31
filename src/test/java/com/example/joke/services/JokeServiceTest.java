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

import java.util.ArrayList;
import java.util.List;

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


    //CREATE


    @BeforeEach
    public void save_validJoke_returnsJoke() {
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
        //Exercise
        Joke actual1 = jokeService.save(joke1);
        Joke actual2 = jokeService.save(joke2);
        Joke actual3 = jokeService.save(joke3);
        //Assert
        assertEquals(joke1.hashCode(), actual1.hashCode());
        assertEquals(joke2.hashCode(), actual2.hashCode());
        assertEquals(joke3.hashCode(), actual3.hashCode());

    }


    //READ


    @Test
    public void findAll_daoWithExistingJokes_returnsJokeList() {
        //Setup
        List<Joke> expected = new ArrayList<>();
        expected.add(joke1);
        expected.add(joke2);
        expected.add(joke3);
        //Exercise
        List<Joke> actual = jokeService.findAll();
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

}