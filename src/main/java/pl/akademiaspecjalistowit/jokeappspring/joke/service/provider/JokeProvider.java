package pl.akademiaspecjalistowit.jokeappspring.joke.service.provider;

import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;

public interface JokeProvider {

    Joke getJoke();

    Joke getJokeByCategory(String category);
}
