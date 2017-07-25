package com.romodaniel.newsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.romodaniel.newsapp.data.DBHelper;
import com.romodaniel.newsapp.data.Contract;
import com.romodaniel.newsapp.data.DatabeUtils;


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Void>, ArticleAdapter.ItemClickListener {

    static final String TAG = "mainactivity";
    private static final int NEWS_LOADER = 1 ;

    private ProgressBar progress;
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private Cursor cursor;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_article);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirst = prefs.getBoolean("isfirst", true);

        if(isFirst) {
            load();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isfirst", false);
            editor.commit();
        }

      ScheduleUtilities.scheduleRefresh(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        db = new DBHelper(MainActivity.this).getReadableDatabase();
        cursor = DatabeUtils.getAll(db);
        articleAdapter = new ArticleAdapter(cursor, this);
        recyclerView.setAdapter(articleAdapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemNumber = item.getItemId();

        if(itemNumber == R.id.search){
            load();
        }
        return true;
    }

    @Override
    public Loader<Void> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Void>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public Void loadInBackground() {
                RefreshTasks.refreshArticles(MainActivity.this);
                return null;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Void> loader, Void data) {

        progress.setVisibility(View.GONE);
        db = new DBHelper(MainActivity.this).getReadableDatabase();
        cursor = DatabeUtils.getAll(db);

        articleAdapter = new ArticleAdapter(cursor, this);
        recyclerView.setAdapter(articleAdapter);
        articleAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(Loader<Void> loader) {

    }


    public void load(){
        LoaderManager loaderManager = getSupportLoaderManager();
       loaderManager.restartLoader(NEWS_LOADER,null,this).forceLoad();
    }

    @Override
    public void onItemClick(Cursor cursor, int clickedItemIndex) {
        cursor.moveToPosition(clickedItemIndex);
        String url = cursor.getString(cursor.getColumnIndex(Contract.TABLE_ARTICLES.COLUMN_NAME_URL));
        Log.d(TAG, String.format("Url %s", url));

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
