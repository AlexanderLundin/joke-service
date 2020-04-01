package com.example.joke.repositories;

import com.example.joke.entities.Joke;
import com.example.joke.entities.JokeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeDao extends JpaRepository<Joke, Long> {
    @Query("SELECT j FROM Joke j WHERE jokeType = ?1")
    List<Joke> findAllByJokeType(JokeType jokeType);

    @Query("SELECT j FROM Joke j WHERE jokeContent LIKE %?1% AND jokeType = ?2")
    List<Joke> findAllBySearchStringAndJokeType(String searchString, JokeType jokeType);

    @Query("SELECT j FROM Joke j WHERE jokeContent LIKE %?1%")
    List<Joke> findAllBySearchString(String searchString);

    //bonus feature
//    @Query(value = "SELECT * FROM jokes j WHERE jokeType LIKE ?1 order by RAND() LIMIT 1", nativeQuery= true)
//    Joke findRandomJoke(String jokeType);

    @Query(value = "select * from jokes j order by RAND() LIMIT 1", nativeQuery= true)
    Joke findRandomJoke();
}
