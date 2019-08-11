package id.shobrun.moviecatalogue.models;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.data.Movie;

public interface InterfaceMovieModel {
    ArrayList<Movie> getAllMovies();
    Movie getMovie(int position);
    int getMovieById(int id);
    int getMovieCount();
}
