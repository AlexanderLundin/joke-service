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
    private Joke joke4;
    private Joke joke5;
    private Joke joke6;
    private String jokeContent;
    private JokeType jokeType;
    private List<Joke> jokeList;


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
        jokeContent = "How do you make holy water? You boil the hell out of it.";
        jokeType = JokeType.DADJOKES;
        joke4 = new Joke(jokeType, jokeContent);
        jokeContent = "Why is a computer so smart?Because it listens to its motherboard.";
        jokeType = JokeType.MOMJOKES;
        joke5 = new Joke(jokeType, jokeContent);
        jokeContent = "What's the difference between a motorcycle and a vacuum cleaner. One can fit two dirt bags on it.";
        jokeType = JokeType.KIDJOKES;
        joke6 = new Joke(jokeType, jokeContent);
        //Exercise
        Joke actual1 = jokeService.save(joke1);
        Joke actual2 = jokeService.save(joke2);
        Joke actual3 = jokeService.save(joke3);
        Joke actual4 = jokeService.save(joke4);
        Joke actual5 = jokeService.save(joke5);
        Joke actual6 = jokeService.save(joke6);
        jokeList = new ArrayList<>();
        jokeList.add(joke1);
        jokeList.add(joke2);
        jokeList.add(joke3);
        jokeList.add(joke4);
        jokeList.add(joke5);
        jokeList.add(joke6);
        //Assert
        assertEquals(joke1.hashCode(), actual1.hashCode());
        assertEquals(joke2.hashCode(), actual2.hashCode());
        assertEquals(joke3.hashCode(), actual3.hashCode());
        assertEquals(joke4.hashCode(), actual4.hashCode());
        assertEquals(joke5.hashCode(), actual5.hashCode());
        assertEquals(joke6.hashCode(), actual6.hashCode());
    }


    //READ


    @Test
    public void findAll_daoWithExistingJokes_returnsJokeList() {
        //Setup
        List<Joke> expected = jokeList;
        //Exercise
        List<Joke> actual = jokeService.findAll();
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

    @Test
    public void findById_daoWithExistingJokes_returnsJokeList() {
        //Setup
        Joke expected = joke1;
        Long id = joke1.getId();
        //Exercise
        Joke actual = jokeService.findById(id);
        //Assert
        assertEquals(expected.hashCode(), actual.hashCode());
        //Teardown
    }

    @Test
    public void findAllByJokeType_daoWithExistingJokes_returnsJokeList() {
        //Setup
        List<Joke> expected = new ArrayList<>();
        expected.add(joke1);
        expected.add(joke2);
        expected.add(joke3);
        //Exercise
        List<Joke> actual = jokeService.findAllByJokeType(JokeType.TECHNOLOGY);
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

    @Test
    public void findAllBySearchStringAndJokeType_daoWithExistingJokes_returnsJokeList() {
        //Setup
        List<Joke> expected = new ArrayList<>();
        expected.add(joke1);
        //Exercise
        String searchString = "database";
        List<Joke> actual = jokeService.findAllBySearchStringAndJokeType(searchString, JokeType.TECHNOLOGY);
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

    @Test
    public void findAllBySearchStringAndNullJokeType_daoWithExistingJokes_returnsJokeList() {
        //Setup
        List<Joke> expected = new ArrayList<>();
        expected.add(joke1);
        //Exercise
        String searchString = "database";
        List<Joke> actual = jokeService.findAllBySearchStringAndJokeType(searchString, null);
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }


    @Test
    public void findRandomJoke_daoWithExistingJokesByJokeType_returnsJoke() {
        //Setup
        //Exercise
        Joke actual = jokeService.findRandomJoke(JokeType.TECHNOLOGY);
        //Assert
        assertNotNull(actual);
        //Teardown
    }

    @Test
    public void findRandomJoke_daoWithExistingJokes_returnsJoke() {
        //Setup
        //Exercise
        Joke actual = jokeService.findRandomJoke(null);
        //Assert
        assertNotNull(actual);
        //Teardown
    }


    //UPDATE


    @Test
    public void updateJokeById_daoWithJoke_returnsJoke() {
        //Setup
        Joke expected = new Joke();
        //Exercise
        Joke newJoke = jokeService.save(expected);
        Long id = newJoke.getId();
        newJoke.setJokeType(JokeType.KNOCKKNOCK);
        Joke actual = jokeService.updateJokeById(id, expected);
        //Assert
        assertEquals(JokeType.KNOCKKNOCK, actual.getJokeType());
        //Teardown
        jokeService.delete(id);
    }


    //DELETE


    @Test
    public void deleteById_doaWithJoke_returnsVoid() {
        //Setup
        Joke joke = new Joke();
        joke = jokeService.save(joke);
        Long id = joke.getId();
        //Exercise
        jokeService.delete(id);
        Joke actual = jokeService.findById(id);
        //Assert
        assertNull(actual);
    }
}