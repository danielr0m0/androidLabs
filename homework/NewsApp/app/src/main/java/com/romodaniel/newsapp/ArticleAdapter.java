package com.romodaniel.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.romodaniel.newsapp.model.NewsItem;

import java.util.ArrayList;


/**
 * Created by drdan on 6/27/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder> {

    private ArrayList<NewsItem> data;
    ItemClickListener listener;

    public ArticleAdapter(ArrayList<NewsItem> data, ItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int clickedItemIndex);
    }


    @Override
    public ArticleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
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
        return data.size();
    }

    public class ArticleAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView articleTitleView;
        TextView articleDescriptionView;
        TextView articleTimeView;



        public ArticleAdapterViewHolder(View itemView) {
            super(itemView);
            articleTitleView = (TextView) itemView.findViewById(R.id.article_title);
            articleDescriptionView = (TextView) itemView.findViewById(R.id.article_description);
            articleTimeView = (TextView) itemView.findViewById(R.id.article_time);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            NewsItem art = data.get(position);
            articleTitleView.setText(art.getTitle());
            articleDescriptionView.setText(art.getDescription());
            articleTimeView.setText(art.getDate());
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos);
        }
    }
}
