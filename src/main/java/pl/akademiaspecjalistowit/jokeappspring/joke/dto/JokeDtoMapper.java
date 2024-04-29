package pl.akademiaspecjalistowit.jokeappspring.joke.dto;

import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;

public interface JokeDtoMapper {

    static Joke toJoke(JokeDto jokeDto) {
        return new Joke(jokeDto.getSetup() + "\n" + jokeDto.getDelivery(),
                jokeDto.getCategory());
    }
}
