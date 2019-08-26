package com.udacity.mylibrary;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SingleJokeActivity extends AppCompatActivity {


    public static final String JOKE_INTENT_KEY = "joke_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_joke);

        if(getIntent().getStringExtra(JOKE_INTENT_KEY) != null)
        {
            Toast.makeText(getApplicationContext() , "Joke : " + getIntent().getStringExtra(JOKE_INTENT_KEY) , Toast.LENGTH_LONG).show();
        }
    }
}
