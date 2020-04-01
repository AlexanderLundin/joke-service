package com.example.joke.controllers;

import com.example.joke.entities.Joke;
import com.example.joke.entities.JokeType;
import com.example.joke.services.JokeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class JokeController {

    private JokeService jokeService;

    public JokeController(JokeService jokeService){
        this.jokeService = jokeService;
    }


    //READ

    
    @GetMapping("/jokes")
    public List<Joke> getAllJokes(){
        return jokeService.findAll();
    }

    @GetMapping("/jokes/{jokeType}")
    public List<Joke> getAllJokesByCategory(@PathVariable JokeType jokeType){
        return jokeService.findAllByJokeType(jokeType);
    }
}
