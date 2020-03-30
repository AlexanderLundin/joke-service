package com.example.joke.entities;

public class Joke {

    private JokeType jokeType;
    private String jokeContent;

    public Joke() {
    }

    public Joke(JokeType jokeType, String jokeContent) {
        this.jokeType = jokeType;
        this.jokeContent = jokeContent;
    }


}
