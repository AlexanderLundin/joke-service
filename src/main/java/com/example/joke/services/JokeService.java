package com.example.joke.services;

import com.example.joke.repositories.JokeDao;
import org.springframework.stereotype.Service;

@Service
public class JokeService {
    private JokeDao jokeDao;

    public JokeService (JokeDao jokeDao){
        this.jokeDao = jokeDao;
    }
}
