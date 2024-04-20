package pl.akademiaspecjalistowit.jokeappspring.joke.service.provider;

import pl.akademiaspecjalistowit.jokeappspring.joke.service.JokeServiceException;

public class JokeApiProviderException extends JokeServiceException {

    public JokeApiProviderException(String message) {
        super(message);
    }

    public JokeApiProviderException(String message, Throwable cause) {
        super(message, cause);
    }
}