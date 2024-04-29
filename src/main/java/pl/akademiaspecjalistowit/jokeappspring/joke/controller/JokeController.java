package pl.akademiaspecjalistowit.jokeappspring.joke.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.JokeService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jokes")
public class JokeController {

    private final JokeService jokeService;

    @GetMapping
    public Joke getRandomJoke(@RequestParam(value = "category", required = false)
                              Optional<String> category) {
        return category
                .map(jokeService::getJoke)
                .orElse(jokeService.getJoke());
    }

    @PostMapping
    public ResponseEntity<Joke> saveJoke(@RequestBody Joke joke) {
        Joke savedJoke = jokeService.getJoke();
        HttpHeaders headers = new HttpHeaders();
        headers.add("result", "The joke has been successfully saved!");
        return new ResponseEntity<>(savedJoke, headers, HttpStatus.OK);
    }
}
