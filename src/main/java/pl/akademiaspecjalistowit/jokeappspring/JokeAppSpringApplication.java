package pl.akademiaspecjalistowit.jokeappspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.akademiaspecjalistowit.jokeappspring.configuration.Config;

@SpringBootApplication
public class JokeAppSpringApplication {

    public static void main(String[] args) {

        initiateApplicationContext();

        SpringApplication.run(JokeAppSpringApplication.class, args);
    }

    private static void initiateApplicationContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Config config = context.getBean(Config.class);
    }
}

    //TODO init with spring
//    private static JokeService initiateApplicationContext() {
//        List<JokeRepository> jokeRepositories = List.of(
//            new InMemoryJokeRepository(),
//            new FileJokeRepository("src/main/resources/jokes.txt"));
//
//        List<JokeProvider> jokeProviders =
//            List.of(new JokeApiProvider(HttpClient.newHttpClient()), new JokeDataProvider(jokeRepositories));
//
//        JokeService jokeService = new JokeServiceImpl(jokeProviders);
//        return jokeService;
//    }
