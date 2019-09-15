package id.shobrun.moviecatalogue.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import id.shobrun.moviecatalogue.models.data.Movie;

public class Helper {
    private static final String TAG = Helper.class.getSimpleName();

    public static ArrayList<Movie> mappingCursorToMovies(Cursor cursor){
        ArrayList<Movie>  movies = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Movie._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(Movie.TITLE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(Movie.OVERVIEW));
            String poster_path = cursor.getString(cursor.getColumnIndexOrThrow(Movie.POSTER_PATH));
            String backdrop_path = cursor.getString(cursor.getColumnIndexOrThrow(Movie.BACKDROP_PATH));
            double vote_average = cursor.getDouble(cursor.getColumnIndexOrThrow(Movie.VOTE_AVERAGE));
            String release_date = cursor.getString(cursor.getColumnIndexOrThrow(Movie.RELEASE_DATE));
            String tags = cursor.getString(cursor.getColumnIndexOrThrow(Movie.TAGS));

            Movie movie = new Movie(id,title,overview,poster_path,backdrop_path,vote_average,release_date,tags);
            Log.d(TAG, "mappingCursorToMovies: "+movie.getId());
            movies.add(movie);
        }
        Log.d(TAG, "mappingCursorToMovies size: "+movies.size());
        return  movies;
    }

    public static ContentValues mappingMoviesToContent(Movie movie){
        ContentValues values = new ContentValues();
        Log.d(TAG, "mappingMoviesToContent: "+movie.getId());
        values.put(Movie._ID,movie.getId());
        values.put(Movie.TITLE,movie.getTitle());
        values.put(Movie.OVERVIEW,movie.getOverview());
        values.put(Movie.POSTER_PATH,movie.getPoster_path());
        values.put(Movie.BACKDROP_PATH,movie.getBackdrop_path());
        values.put(Movie.VOTE_AVERAGE,movie.getVote_average());
        values.put(Movie.RELEASE_DATE,movie.getRelease_date());
        values.put(Movie.TAGS,movie.getTags());
        return values;
    }
    public static String addWildcard(String str){
        return "%"+str+"%";
    }

}
