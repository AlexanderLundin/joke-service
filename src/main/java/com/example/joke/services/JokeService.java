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
        Long count;
        Long randomId;
        if(jokeType != null){
            List<Joke> jokeList = jokeDao.findAllByJokeType(jokeType);
            // store id fields of jokes by mapping with the getId method
            List<Long> idList = jokeList.stream().map(Joke::getId).collect(Collectors.toList());
            Random rand = new Random();
            randomId = idList.get(rand.nextInt(idList.size()));
        }else{
            count = jokeDao.count();
            long leftLimit = 1L;
            long rightLimit = count;
            randomId = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        }

        if (jokeDao.existsById(randomId)){
            return jokeDao.findById(randomId).get();
        }else{
            return null;
        }

    }
}
