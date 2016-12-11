package com.yeoggc.aysnctasksimpledemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yeoggc on 2016/12/10.
 */

public class MyAsyncTask extends AsyncTask<String,Void,Bitmap> {

    private static final String Tag = "MyAsyncTask";

    private ImageView mImageView;

    public MyAsyncTask(ImageView imageView){
        this.mImageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e(Tag,"onPreExecute ----------> " + Thread.currentThread().getName());
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Log.e(Tag,"doInBackground ----------> " + Thread.currentThread().getName());

        try {
            URL url = new URL(params[0]);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.e(Tag,"onPostExecute ----------> " + Thread.currentThread().getName());
        mImageView.setImageBitmap(bitmap);
    }
}
