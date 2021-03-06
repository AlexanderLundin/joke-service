package com.example.joke.controllers;

import com.example.joke.entities.Joke;
import com.example.joke.entities.JokeType;
import com.example.joke.services.JokeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class JokeController {

    private JokeService jokeService;

    public JokeController(JokeService jokeService){
        this.jokeService = jokeService;
    }

    //CREATE


    @PostMapping("/jokes")
    public Joke postJoke(@RequestBody Joke joke){
        return jokeService.save(joke);
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

    @GetMapping("/jokes/{searchString}/{jokeType}")
    public List<Joke> getAllJokesByCategoryAndJokeType(@PathVariable String searchString, @PathVariable Optional<JokeType> oJokeType){
        JokeType jokeType;
        if (oJokeType.isPresent()) {
            jokeType = oJokeType.get();
        }else{
            jokeType = null;
        }
        return jokeService.findAllBySearchStringAndJokeType(searchString, jokeType);
    }

    @GetMapping("/jokes/random/{jokeType}")
    public Joke getRandomJokeByOptionalJokeType(@PathVariable (required = false) JokeType jokeType){
        return jokeService.findRandomJoke(jokeType);
    }
    //UPDATE


    @PatchMapping("/jokes/{id}")
    public Joke patchJoke(@PathVariable Long id, @RequestBody Joke joke){
        return jokeService.updateJokeById(id, joke);
    }


    //DELETE


    @DeleteMapping("/jokes/{id}")
    public void deleteJoke(@PathVariable Long id){
        jokeService.delete(id);
    }
}
