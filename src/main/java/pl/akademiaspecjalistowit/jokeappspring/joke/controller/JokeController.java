package pl.akademiaspecjalistowit.jokeappspring.joke.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.entity.JokeEntity;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.model.Joke;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository.JokeJpaRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.service.JokeService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jokes")
public class JokeController {

    private final JokeService jokeService;
    private final JokeJpaRepository jokeJpaRepository;

    @GetMapping
    public Joke getRandomJoke(@RequestParam(value = "category", required = false)
                              Optional<String> category) {
        return category
                .map(jokeService::getJoke)
                .orElse(jokeService.getJoke());
    }

    @GetMapping("/{id}")
    public Joke getJokeById(@PathVariable Long id) {
        return jokeJpaRepository
                .findById(id)
                .map(e -> new Joke(e.getTechnicalId(), e.getContent(), e.getCategory()))
                .orElseThrow(() -> new RuntimeException("Joke not found"));
    }

    @GetMapping("/category/{category}")
    public List<Joke> getJokeByCategory(@PathVariable String category) {
        return jokeJpaRepository
                .getJokeEntitiesByCategory(category)
                .stream()
                .map(e -> new Joke(e.getTechnicalId(), e.getContent(), e.getCategory()))
                .toList();
    }

    @PostMapping
    public void addJoke(@RequestBody Joke joke) {
        JokeEntity jokeEntity = new JokeEntity(joke.getId(), joke.getContent(), joke.getCategory());
        jokeJpaRepository.save(jokeEntity);
    }
}

//    @PostMapping
//    public ResponseEntity<Joke> saveJoke(@RequestBody Joke joke) {
//        Joke savedJoke = jokeService.getJoke();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("result", "The joke has been successfully saved!");
//        return new ResponseEntity<>(savedJoke, headers, HttpStatus.OK);
//    }
