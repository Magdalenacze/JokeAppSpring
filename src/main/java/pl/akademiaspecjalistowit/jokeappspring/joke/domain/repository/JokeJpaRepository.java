package pl.akademiaspecjalistowit.jokeappspring.joke.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.jokeappspring.joke.domain.entity.JokeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface JokeJpaRepository extends JpaRepository<JokeEntity, Long> {
    List<JokeEntity> getJokeEntitiesByCategory(String category);

    List<JokeEntity> findAll();

    List<JokeEntity> findAllByCategory(String category);
}
