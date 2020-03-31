package com.example.joke.repositories;

import com.example.joke.entities.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeDao extends JpaRepository<Joke, Long> {
}
