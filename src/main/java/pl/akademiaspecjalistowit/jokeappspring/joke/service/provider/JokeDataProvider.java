package pl.akademiaspecjalistowit.jokeappspring.joke.service.provider;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.entity.JokeEntity;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository.JokeJpaRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository.JokeRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.dto.JokeMapper;

@Service
public class JokeDataProvider implements JokeProvider {

    private final List<JokeJpaRepository> jokeRepositories;
    private final JokeMapper jokeMapper;
    private final Random rand;
    private static long counter;

    public JokeDataProvider(List<JokeJpaRepository> jokeRepositories, JokeMapper jokeMapper,
                            Random rand, @Value("0") long counter) {
        this.jokeRepositories = jokeRepositories;
        this.jokeMapper = jokeMapper;
        this.rand = rand;
        this.counter = counter;
    }

    @Override
    public Joke getJoke() {
        List<JokeEntity> anyJokes = getJokeRepository().findAll();
        return jokeMapper.fromEntity(anyJokes.get(rand.nextInt(anyJokes.size())));
    }

    @Override
    public Joke getJokeByCategory(String category) {
        List<JokeEntity> jokesByCategory =
                getJokeRepository().findAllByCategory(category);
        if (jokesByCategory.isEmpty()) {
            throw new JokeDataProviderException("No joke for the selected category!");
        }
        JokeEntity jokeEntity = jokesByCategory.get(rand.nextInt(jokesByCategory.size()));
        return jokeMapper.fromEntity(jokeEntity);
    }

    private JokeJpaRepository getJokeRepository() {
        return jokeRepositories.get((int) counter++ % jokeRepositories.size());
    }
}