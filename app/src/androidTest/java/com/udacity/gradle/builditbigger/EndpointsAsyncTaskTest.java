package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.jokessource.JokeDummyFactory;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testExecuteAsyncMethod() throws Exception {

        Context context = mainActivityActivityTestRule.getActivity().getApplicationContext();

        GetJokeFromCloud getJokeFromCloud = new GetJokeFromCloud(context);
        getJokeFromCloud.execute();
        String jokeFromSource = getJokeFromCloud.get(10, TimeUnit.SECONDS);


        Assert.assertNotNull(jokeFromSource);

        JokeDummyFactory jokes = new JokeDummyFactory();
        ArrayList<String> jokeList = jokes.getJokes();


        Assert.assertTrue("Unexpected value for d.getFormType(): " + jokeFromSource,
                jokeFromSource.equals(jokeList.get(0)) ||
                        jokeFromSource.equals(jokeList.get(1)) ||
                        jokeFromSource.equals(jokeList.get(2))||
                        jokeFromSource.equals(jokeList.get(3))||
                        jokeFromSource.equals(jokeList.get(4))||
                        jokeFromSource.equals(jokeList.get(5)));
    }
}