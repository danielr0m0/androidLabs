package com.romodaniel.newsapp;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by drdan on 6/16/2017.
 */

public class NetworkUtils {
    //https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=
    public static final String NEWS_BASE_URL=
            "https://newsapi.org/v1/articles";

    public static final String PARAM_SOURCE=
            "source";
    public static final String source =
            "the-next-web";

    public static final String PARAM_SORTBY=
            "sortBy";
    public static final String sortBy =
            "latest";

    public static final String PARAM_APIKEY=
            "apiKey";

//    TODO insert your apikey here
    public  static final String apiKey=
            "";

    public static URL buildUrl(){
        Uri uri = Uri.parse(NEWS_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE, source)
                .appendQueryParameter(PARAM_SORTBY,sortBy)
                .appendQueryParameter(PARAM_APIKEY,apiKey)
                .build();

        URL url= null;

        try{
            url= new URL(uri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl (URL url) throws IOException{
        HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }else{
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
