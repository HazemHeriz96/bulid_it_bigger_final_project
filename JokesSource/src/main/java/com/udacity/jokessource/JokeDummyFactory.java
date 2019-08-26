package com.udacity.jokessource;

import java.util.Random;

public class JokeDummyFactory {

    private String[] jokes = {
            "First Joke , Hello World ",
            "Secont Joke , Hello World ",
            "Third Joke , Hello World ",
            "Fourth Joke , Hello World ",
            "Fivth Joke , Hello World ",

    };

    public String randomJoke()
    {

        return  jokes[new Random().nextInt(jokes.length)];
    }
}
