package pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository;

import java.util.List;

import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;

public interface JokeRepository {

    List<Joke> getAllJokes();

    List<Joke> getAllByCategory(String category);
}
