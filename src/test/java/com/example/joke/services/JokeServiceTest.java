package com.example.joke.services;

import com.example.joke.repositories.JokeDao;
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


    //@Autowired
    //JokeDao jokeDao;


    @Test
    public void jokeServiceConstructorInjection() {
        //Setup
        System.out.println("test");
        //Exercise

        //Assert
        //assertEquals(expected, actual);
        //Teardown
    }

}