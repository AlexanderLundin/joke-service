package com.example.joke.services;

import com.example.joke.entities.Joke;
import com.example.joke.entities.JokeType;
import com.example.joke.repositories.JokeDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class JokeService {
    private JokeDao jokeDao;

    public JokeService (JokeDao jokeDao){
        this.jokeDao = jokeDao;
    }

    public Joke save(Joke joke) {
        return jokeDao.save(joke);
    }

    public List<Joke> findAll() {
        return jokeDao.findAll();
    }

    public List<Joke> findAllByJokeType(JokeType jokeType) {
        return jokeDao.findAllByJokeType(jokeType);
    }
}
