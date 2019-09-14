package id.shobrun.moviecatalogue.utils;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import id.shobrun.moviecatalogue.models.data.Movie;

public class Helper {
    public static ArrayList<Movie> mappingCursorToMovies(Cursor cursor){
        ArrayList<Movie>  movies = new ArrayList<>();
        while(cursor.moveToNext()){

        }
        return  movies;
    }
    public static ContentValues mappingMoviesToContent(Movie movie){
        ContentValues values = new ContentValues();

        return values;
    }

}
