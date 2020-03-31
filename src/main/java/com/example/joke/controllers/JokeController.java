package com.example.joke.controllers;

import com.example.joke.entities.Joke;
import com.example.joke.services.JokeService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/jokes")
    public List<Joke> getAllJokes(){
        return jokeService.findAll();
    }
}
