package com.romodaniel.newsapp;

import android.net.Uri;

import com.romodaniel.newsapp.model.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
            "7369b6d11aad4d7899f70be9cae53ff8";

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

    public static ArrayList<Article> parseJSON(String json) throws JSONException{
        ArrayList<Article> results = new ArrayList<>();
        JSONObject main = new JSONObject(json);
        JSONArray articles = main.getJSONArray("articles");

        for (int i =0; i <articles.length(); i++){
            JSONObject article = articles.getJSONObject(i);
            String author = article.getString("author");
            String title = article.getString("title");
            String url = article.getString("url");
            String description = article.getString("description");
            Article art = new Article(author,title,description,url);
            results.add(art);
        }
        return results;
    }
}
