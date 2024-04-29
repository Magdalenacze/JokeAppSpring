package pl.akademiaspecjalistowit.jokeappspring.joke.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository.FileJokeRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository.InMemoryJokeRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository.JokeRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.dto.JokeMapper;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.JokeService;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.JokeServiceImpl;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.provider.JokeApiProvider;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.provider.JokeDataProvider;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.provider.JokeProvider;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Random;

@Configuration
public class JokeBeans {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public JokeMapper jokeMapper() {
        return new JokeMapper();
    }

    @Bean
    public Random rand() {
        return new Random();
    }

    @Bean
    public List<Joke> jokes() {
        return List.of(
                new Joke("What is an object-oriented way to make a fortune? Inheritance",
                        "Programmers"),
                new Joke("What does an IT specialist say when he receives a pendrive for " +
                        "his birthday? Thanks for remembering",
                        "Programmers"),
                new Joke("What is the difference between programmers and politicians? " +
                        "Programmers are only paid for programs that work",
                        "Politics"),
                new Joke("How can you tell if a politician is lying? By the way he moves his mouth...",
                        "Politics"),
                new Joke("How on earth did you manage to become a professional bodybuilder " +
                        "when you were always a loser in Physical Education? Because I was always a top student in chemistry",
                        "Sport"),
                new Joke("Why does a Polish national team fan need a scarf? " +
                        "So that he would have something to wipe his tears with",
                        "Sport"));
    }

    @Bean
    public List<JokeRepository> jokeRepositories() {
        return List.of(
                new InMemoryJokeRepository(jokes()),
                new FileJokeRepository("src/main/resources/jokes.json", objectMapper()));
    }

    @Bean
    public List<JokeProvider> jokeProviders() {
        return List.of(
                new JokeApiProvider(httpClient(), "https://v2.jokeapi.dev/joke/Any", "https://v2.jokeapi.dev/joke/"),
                new JokeDataProvider(jokeRepositories(), jokeMapper(), rand(), 0));
    }

    @Bean
    public JokeService jokeService() {
        return new JokeServiceImpl(jokeProviders(), 0);
    }
}
