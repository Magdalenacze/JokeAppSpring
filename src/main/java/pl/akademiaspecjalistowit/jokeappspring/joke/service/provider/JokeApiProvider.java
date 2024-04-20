package pl.akademiaspecjalistowit.jokeappspring.joke.service.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.jokeappspring.joke.dto.JokeDto;
import pl.akademiaspecjalistowit.jokeappspring.joke.mapper.JokeDtoMapper;
import pl.akademiaspecjalistowit.jokeappspring.joke.model.Joke;

@Service
public class JokeApiProvider implements JokeProvider {

    private final HttpClient httpClient;
    private final String urlToAnyCategory;
    private final String urlToSpecificCategory;

    public JokeApiProvider(HttpClient httpClient,
                           @Value("${jokes.externalApi.urlToJokeApi.urlToAnyCategory}")
                           String urlToAnyCategory,
                           @Value("${jokes.externalApi.urlToJokeApi.urlToSpecificCategory}")
                           String urlToSpecificCategory) {
        this.httpClient = httpClient;
        this.urlToAnyCategory = urlToAnyCategory;
        this.urlToSpecificCategory = urlToSpecificCategory;
    }

    @Override
    public Joke getJoke() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlToAnyCategory))
                .build();
        return getResponse(request);
    }

    @Override
    public Joke getJokeByCategory(String category) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urlToSpecificCategory + category))
                .build();
        return getResponse(request);
    }

    private Joke getResponse(HttpRequest request) {
        try {
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Joke joke = JokeDtoMapper.toJoke(objectMapper.readValue(response.body(), JokeDto.class));
            return joke;
        } catch (RuntimeException e) {
            throw new JokeDataProviderException("Failed to all external API!");
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}