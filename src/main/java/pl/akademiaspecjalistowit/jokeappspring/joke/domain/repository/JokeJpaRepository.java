package pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.entity.JokeEntity;

import java.util.List;

public interface JokeJpaRepository extends JpaRepository<JokeEntity, Long> {
    List<JokeEntity> findAll();

    List<JokeEntity> findAllByCategory(String category);
}
