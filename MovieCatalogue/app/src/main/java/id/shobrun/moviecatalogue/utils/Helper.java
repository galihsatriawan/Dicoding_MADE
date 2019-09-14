package id.shobrun.moviecatalogue.utils;

import android.database.Cursor;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.Movie;

public class Helper {
    static ArrayList<Movie> mappingCursorToMovies(Cursor cursor){
        ArrayList<Movie>  movies = new ArrayList<>();
        while(cursor.moveToNext()){

        }
        return  movies;
    }
}
