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

    public JokeType getJokeType() {
        return jokeType;
    }

    public void setJokeType(JokeType jokeType) {
        this.jokeType = jokeType;
    }

    public String getJokeContent() {
        return jokeContent;
    }

    public void setJokeContent(String jokeContent) {
        this.jokeContent = jokeContent;
    }

}
