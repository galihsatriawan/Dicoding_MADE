package id.shobrun.moviecatalogue.model;

import android.content.Context;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.data.MoviesData;

public class MovieModel implements InterfaceMovieModel {
    private ArrayList<Movie> movies;
    private Context ctx;
    public MovieModel(Context ctx) {
        this.ctx = ctx;
        MoviesData moviesData = new MoviesData(ctx);
        movies = moviesData.getMovies();
    }

    @Override
    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    @Override
    public Movie getMovie(int position) {
        Movie movie = null;
        try{
             movie =movies.get(position);
        }catch (Exception e){
            e.printStackTrace();
        }

        return movie;
    }

    @Override
    public int getMovieById(int id) {
        int position =-1 ;
        int index = 0;
        for(Movie movie : movies){
            if(movie.getId()==id) {
                position = index;
                return position;
            }
            index++;
        }
        return position;
    }

    @Override
    public int getMovieCount() {
        return movies.size();
    }


}
