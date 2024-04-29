package pl.akademiaspecjalistowit.jokeappspring.joke.dto;

import org.springframework.stereotype.Component;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.entity.JokeEntity;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;

@Component
public class JokeMapper {

    public Joke fromEntity(JokeEntity jokeEntity) {
        return new Joke(
                jokeEntity.getTechnicalId(),
                jokeEntity.getContent(),
                jokeEntity.getCategory());
    }
}
