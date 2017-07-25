package com.romodaniel.newsapp.data;

/**
 * Created by drdan on 6/27/2017.
 */

public class NewsItem {
    private String Author;
    private String Title;
    private String Description;
    private String ImageUrl;
    private String Url;
    private String Date;



    public NewsItem(String author, String title, String description, String imageUrl, String url, String date) {
        Author = author;
        Title = title;
        Description = description;
        ImageUrl = imageUrl;
        Url = url;
        Date = date;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return
                "Author= " + Author + "\n\n" +
                "Title= " + Title + "\n\n" +
                "Description= " + Description + "\n\n" +
                "Date=" + Date + "\n\n"
                ;
    }


}
