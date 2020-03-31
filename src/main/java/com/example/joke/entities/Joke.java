package com.example.joke.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jokes")
public class Joke {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "joke_type")
    private JokeType jokeType;
    @Column(name = "joke_content")
    private String jokeContent;

    public Joke() {
    }

    public Joke(JokeType jokeType, String jokeContent) {
        this.jokeType = jokeType;
        this.jokeContent = jokeContent;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joke)) return false;
        Joke joke = (Joke) o;
        return Objects.equals(id, joke.id) &&
                jokeType == joke.jokeType &&
                Objects.equals(jokeContent, joke.jokeContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jokeType, jokeContent);
    }
}
