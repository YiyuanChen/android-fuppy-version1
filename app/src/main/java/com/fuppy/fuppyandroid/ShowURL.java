package com.fuppy.fuppyandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ayumu on 16/10/25.
 */
//For return a bitmap by given url address through an AsynctTask
class ShowURL extends AsyncTask<String, Void, Bitmap> {

    private Bitmap bitmap;

    @Override
    protected Bitmap doInBackground(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        BufferedReader in = null;

        try {
            //Get URL
            URL pic = new URL(params[0]);
            //open and analyze Stream, and get bitmap
            bitmap = BitmapFactory.decodeStream(pic.openStream());

            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap pic) {

    }
}

