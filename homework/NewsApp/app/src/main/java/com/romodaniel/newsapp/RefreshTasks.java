package com.romodaniel.newsapp;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.romodaniel.newsapp.data.DBHelper;
import com.romodaniel.newsapp.data.DatabeUtils;
import com.romodaniel.newsapp.data.NewsItem;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by drdan on 7/24/2017.
 */

public class RefreshTasks {

    public static void refreshArticles(Context context) {
        ArrayList<NewsItem> result = null;
        URL url = NetworkUtils.buildUrl();
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();

        try {
            DatabeUtils.deleteAll(db);
            String json = NetworkUtils.getResponseFromHttpUrl(url);
            result = NetworkUtils.parseJSON(json);
            DatabeUtils.bulkInsert(db, result);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        db.close();
    }
}

