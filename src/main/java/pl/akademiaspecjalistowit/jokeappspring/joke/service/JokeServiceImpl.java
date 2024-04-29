package pl.akademiaspecjalistowit.jokeappspring.joke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository.JokeJpaRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.provider.JokeDataProviderException;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.provider.JokeProvider;

@Service
public class JokeServiceImpl implements JokeService {

    private final List<JokeProvider> jokeProviders;
    private static long counter;

    public JokeServiceImpl(List<JokeProvider> jokeProviders,
                           @Value("0") long counter) {
        if (jokeProviders == null || jokeProviders.isEmpty()) {
            throw new RuntimeException("Required at least one JokeProvider for the application to run");
        }
        this.jokeProviders = jokeProviders;
        this.counter = counter;
    }

    @Override
    public Joke getJoke() {
        Joke joke = getJokeProvider().getJoke();
        return joke;
    }

    @Override
    public Joke getJoke(String category) {
        try {
            return getJokeProvider().getJokeByCategory(category);
        } catch(JokeDataProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JokeProvider getJokeProvider() {
        return jokeProviders.get((int) counter++ % jokeProviders.size());
    }
}
