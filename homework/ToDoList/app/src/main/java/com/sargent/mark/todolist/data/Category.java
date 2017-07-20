package com.sargent.mark.todolist.data;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by drdan on 7/16/2017.
 */

public enum Category {
    WORK,
    SCHOOL,
    HOME,
    IDK,
    ALL;

    //do not want users to select all in thier category
    public static ArrayList<Category> getList(){
        Category[] cat = Category.values();
        ArrayList<Category> categories = new ArrayList<Category>(Arrays.asList(cat));
        categories.remove(cat.length -1);
        return categories;
    }
}
