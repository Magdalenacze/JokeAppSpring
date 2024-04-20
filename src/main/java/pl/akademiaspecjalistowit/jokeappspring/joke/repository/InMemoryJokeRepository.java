package pl.akademiaspecjalistowit.jokeappspring.joke.repository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;

@Repository
public class InMemoryJokeRepository implements JokeRepository {

    private final List<Joke> jokes;

    public InMemoryJokeRepository(List<Joke> jokes) {
        this.jokes = jokes;
    }

    @Override
    public List<Joke> getAllJokes() {
        return jokes;
    }

    @Override
    public List<Joke> getAllByCategory(String category) {
        return jokes
            .stream()
            .filter(compareCategories(category))
            .collect(Collectors.toList());
    }

    private static Predicate<Joke> compareCategories(String category) {
        return c -> c.getCategory().equals(category);
    }
}
