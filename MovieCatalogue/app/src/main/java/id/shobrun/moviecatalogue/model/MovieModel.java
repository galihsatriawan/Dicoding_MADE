package id.shobrun.moviecatalogue.model;

import android.content.Context;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.data.MoviesData;

public class MovieModel implements InterfaceMovieModel {
    private ArrayList<Movie> movies;

    public MovieModel(Context ctx) {
        Context ctx1 = ctx;
        MoviesData moviesData = new MoviesData(ctx);
        movies = moviesData.getMovies();
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
