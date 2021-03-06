package com.example.joke.controllers;

import com.example.joke.entities.Joke;
import com.example.joke.entities.JokeType;
import com.example.joke.services.JokeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class JokeControllerTest {


    @Autowired
    MockMvc mvc;


    @Autowired
    ObjectMapper objectMapper;


    @Autowired
    private JokeService jokeService;


    private Joke joke1;
    private Joke joke2;
    private Joke joke3;
    private Joke joke4;
    private Joke joke5;
    private Joke joke6;
    private String jokeContent;
    private JokeType jokeType;
    private List<Joke> jokeList;


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


    //CREATE


    @Test
    public void postJoke_daoWithoutThisJokeExisting_returnsJoke() throws Exception {
        //Setup
        String url = "/api/jokes";
        Joke expected = new Joke();
        //Exercise
        ResultActions resultActions = mvc.perform(post(url)
                .content(objectMapper.writeValueAsString(expected))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Joke actual = objectMapper.readValue(contentAsString, Joke.class);
        //Assert
        assertNotNull(actual.equals(expected));
        //Teardown
    }


    //READ


    @Test
    public void getAllJokes_daoWithJokes_returnsJokeList() throws Exception {
        //Setup
        String url = "/api/jokes";
        List<Joke> expected = jokeList;
        //Exercise
        ResultActions resultActions = mvc.perform(get(url));
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Joke> actual = Arrays.asList(objectMapper.readValue(contentAsString, Joke[].class));
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

    @Test
    public void getAllJokesByCategory_daoWithJokes_returnsJokeList() throws Exception {
        //Setup
        String url = "/api/jokes/" + JokeType.TECHNOLOGY;
        //Exercise
        ResultActions resultActions = mvc.perform(get(url));
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Joke> actual = Arrays.asList(objectMapper.readValue(contentAsString, Joke[].class));
        //Assert
        assertEquals(3, actual.size());
        //Teardown
    }

    @Test
    public void getAllJokesWithSearchStringAndCategory_daoWithJokes_returnsJokeList() throws Exception {
        //Setup
        String url = "/api/jokes/" + "interesting" + "/" + JokeType.TECHNOLOGY;
        //Exercise
        ResultActions resultActions = mvc.perform(get(url));
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Joke> actual = Arrays.asList(objectMapper.readValue(contentAsString, Joke[].class));
        //Assert
        assertEquals(3, actual.size());
        //Teardown
    }

    @Test
    public void getRandomJokeByOptionalJokeType_jokeTypePresentAndDaoWithJokes_returnsJokeList() throws Exception {
        //Setup
        String url = "/api/jokes/random/" + JokeType.TECHNOLOGY;
        //Exercise
        ResultActions resultActions = mvc.perform(get(url));
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        //objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        Joke actual = objectMapper.readValue(contentAsString, Joke.class);
        //Assert
        assertEquals(JokeType.TECHNOLOGY, actual.getJokeType());
        //Teardown
    }

    //UPDATE


    @Test
    public void patchJoke_daoWithThisJokeExisting_returnsJoke() throws Exception {
        //Setup
        String url = "/api/jokes/" + joke1.getId();
        Joke expected = new Joke();
        //Exercise
        ResultActions resultActions = mvc.perform(patch(url)
                .content(objectMapper.writeValueAsString(expected))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Joke actual = objectMapper.readValue(contentAsString, Joke.class);
        //Assert
        assertNotNull(actual.equals(expected));
        //Teardown
    }


    //DELETE


    @Test
    public void deleteJoke_daoWithThisJokeExisting_returnsNull() throws Exception {
        //Setup
        String url = "/api/jokes/1";
        Joke expected = null;
        //Exercise
        ResultActions resultActions = mvc.perform(delete(url))
                .andExpect(status().isOk());
        //Teardown
    }

}