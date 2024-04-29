package pl.akademiaspecjalistowit.jokeappspring.joke.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Table(name = "JOKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JokeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tech_id")
    private UUID technicalId;

    private String content;
    private String category;

    public JokeEntity(UUID technicalId, String content, String category) {
        this.technicalId = technicalId;
        this.content = content;
        this.category = category;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JokeEntity joke = (JokeEntity) o;
        return Objects.equals(content, joke.content) && Objects.equals(category, joke.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, category);
    }
}