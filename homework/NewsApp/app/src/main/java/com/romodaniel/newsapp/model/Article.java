package com.romodaniel.newsapp.model;

/**
 * Created by drdan on 6/27/2017.
 */

public class Article {
    private String Author;
    private String Title;
    private String Description;
    private String Url;

    public Article(String author, String title, String description, String url) {
        Author = author;
        Title = title;
        Description = description;
        Url = url;
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

    @Override
    public String toString() {
        return
                "Author= " + Author + "\n" +
                ", Title= " + Title + "\n" +
                ", Description= " + Description + "\n" +
                ", Url='" + Url + "\n"
                ;
    }

}
