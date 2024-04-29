package pl.akademiaspecjalistowit.jokeappspring.joke.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.provider.JokeProvider;

public interface JokeService {

    Joke getJoke();

    Joke getJoke(String category);
}
