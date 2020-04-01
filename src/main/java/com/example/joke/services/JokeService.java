package com.example.joke.services;

import com.example.joke.entities.Joke;
import com.example.joke.entities.JokeType;
import com.example.joke.repositories.JokeDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class JokeService {
    private JokeDao jokeDao;

    public JokeService (JokeDao jokeDao){
        this.jokeDao = jokeDao;
    }


    //CREATE


    public Joke save(Joke joke) {
        return jokeDao.save(joke);
    }


    //READ


    public List<Joke> findAll() {
        return jokeDao.findAll();
    }

    public Joke findById(Long id) {
        if (jokeDao.existsById(id)){
            return jokeDao.findById(id).get();
        }else{
            return null;
        }
    }

    public List<Joke> findAllByJokeType(JokeType jokeType) {
        return jokeDao.findAllByJokeType(jokeType);
    }

    public List<Joke> findAllBySearchStringAndJokeType(String searchString, JokeType jokeType) {
        if(jokeType != null){
            return jokeDao.findAllBySearchStringAndJokeType(searchString, jokeType);
        }else{
            return jokeDao.findAllBySearchString(searchString);
        }

    }

    public Joke findRandomJoke(JokeType jokeType) {
        if(jokeType != null){
            return jokeDao.findRandomJoke(jokeType);
        }else{
            return jokeDao.findRandomJoke();
        }
    }


    //UPDATE


    public Joke updateJokeById(Long id, Joke joke) {
        if (jokeDao.existsById(id)){
            joke.setId(id);
            return jokeDao.saveAndFlush(joke);
        }else{
            return null;
        }
    }


    //DELETE


    public void delete(Long id) {
        if (jokeDao.existsById(id)) {
            jokeDao.deleteById(id);
        }
    }
}
