package id.shobrun.moviecatalogue.model;

import android.content.Context;
import android.content.res.TypedArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.data.MoviesData;

public class MovieModel implements InterfaceMovieModel {
    private ArrayList<Movie> movies;
    private MoviesData moviesData;
    private Context ctx;
    public MovieModel(Context ctx) {
        this.ctx = ctx;
        this.moviesData = new MoviesData(ctx);
        movies = this.moviesData.getMovies();
    }

    @Override
    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    @Override
    public Movie getMovie(int position) {
        return movies.get(position);
    }

    @Override
    public int getMovieById(int id) {
        int position =-1 ;
        for(Movie movie : movies){
            if(movie.getId()==id) return position;
        }
        return position;
    }

    @Override
    public int getMovieCount() {
        return movies.size();
    }


}
