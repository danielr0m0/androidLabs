package com.romodaniel.newsapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.romodaniel.newsapp.data.Contract;
import com.romodaniel.newsapp.data.NewsItem;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;


/**
 * Created by drdan on 6/27/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder> {

    private ArrayList<NewsItem> data;
    private Cursor cursor;
    private Context context;
    public static final String TAG = "articleAdapter";
    ItemClickListener listener;

    public ArticleAdapter(Cursor cursor, ItemClickListener listener) {
        this.cursor = cursor;
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(Cursor cursor, int clickedItemIndex);
    }


    @Override
    public ArticleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAddToParentImmediately= false;

        View view = inflater.inflate(R.layout.article,parent,shouldAddToParentImmediately);
        ArticleAdapterViewHolder holder = new ArticleAdapterViewHolder(view);
        
        return holder;

    }

    @Override
    public void onBindViewHolder(ArticleAdapterViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ArticleAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView articleTitleView;
        TextView articleDescriptionView;
        TextView articleTimeView;
        ImageView articleImageView;



        public ArticleAdapterViewHolder(View itemView) {
            super(itemView);
            articleTitleView = (TextView) itemView.findViewById(R.id.article_title);
            articleDescriptionView = (TextView) itemView.findViewById(R.id.article_description);
            articleTimeView = (TextView) itemView.findViewById(R.id.article_time);
            articleImageView = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            cursor.moveToPosition(position);
            articleTitleView.setText(cursor.getString(cursor.getColumnIndex(Contract.TABLE_ARTICLES.COLUMN_NAME_TITLE)));
            articleDescriptionView.setText(cursor.getString(cursor.getColumnIndex(Contract.TABLE_ARTICLES.COLUMN_NAME_DESCRIPTION)));
            articleTimeView.setText(cursor.getString(cursor.getColumnIndex(Contract.TABLE_ARTICLES.COLUMN_NAME_PUBLISHED_DATE)));
            String url = cursor.getString(cursor.getColumnIndex(Contract.TABLE_ARTICLES.COLUMN_NAME_IMAGE_URL));
            Log.d("urltoimg", url);
            if(url != null){
                Picasso.with(context)
                        .load(url)
                        .into(articleImageView);
            }
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(cursor, pos);
        }
    }
}
