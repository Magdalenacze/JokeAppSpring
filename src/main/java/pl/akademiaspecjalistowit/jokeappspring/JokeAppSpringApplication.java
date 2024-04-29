package pl.akademiaspecjalistowit.jokeappspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.akademiaspecjalistowit.jokeappspring.joke.common.config.JokeBeans;

@SpringBootApplication
public class JokeAppSpringApplication {

    public static void main(String[] args) {

        initiateApplicationContext();

        SpringApplication.run(JokeAppSpringApplication.class, args);
    }

    private static void initiateApplicationContext() {
        new AnnotationConfigApplicationContext(JokeBeans.class);
    }
}