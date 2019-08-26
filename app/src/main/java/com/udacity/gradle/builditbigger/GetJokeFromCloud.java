package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.mylibrary.SingleJokeActivity;

import java.io.IOException;

public class GetJokeFromCloud extends AsyncTask<Void, Void, String> {
    private MyApi services = null;
    private Context mContext;
    private String BASE_URL = "http://10.0.2.2:8080/_ah/api/";

    public GetJokeFromCloud(Context context) {
        this.mContext = context;
    }


    @Override
    protected String doInBackground(Void... params) {

        if (services == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(BASE_URL)

//                    .setGoogleClientRequestInitializer((obj)->{
//                        abstractGoogleClientRequest.setDisableGZipContent(true);
//
//                    })
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        }
                    });

            services = builder.build();
        }

        try {
            return services.tellJoke().execute().getData();

        } catch (IOException e) {
            Log.d("DDDD" , " Error Message : " + e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {

        Intent jokeStage = new Intent(mContext, SingleJokeActivity.class);
        jokeStage.putExtra(SingleJokeActivity.JOKE_INTENT_KEY, joke);
        jokeStage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(jokeStage);
    }
}
