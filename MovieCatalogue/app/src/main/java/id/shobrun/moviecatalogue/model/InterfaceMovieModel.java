package id.shobrun.moviecatalogue.model;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.data.Movie;

public interface InterfaceMovieModel {
    ArrayList<Movie> getAllMovies();
    Movie getMovie(int position);
    int getMovieById(int id);
    int getMovieCount();
}
