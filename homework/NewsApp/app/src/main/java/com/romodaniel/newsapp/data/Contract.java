package com.romodaniel.newsapp.data;

import android.provider.BaseColumns;

/**
 * Created by drdan on 7/24/2017.
 */

public class Contract {

    public static class TABLE_ARTICLES implements BaseColumns {
        public static final String TABLE_NAME = "articles";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PUBLISHED_DATE = "publishedAt";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE_URL = "urlToImage";
        public static final String COLUMN_NAME_URL = "url";
    }
}
