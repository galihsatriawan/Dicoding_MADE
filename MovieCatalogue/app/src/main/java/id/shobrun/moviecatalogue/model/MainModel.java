package id.shobrun.moviecatalogue.model;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.data.Movie;

public class MainModel {

    private ArrayList<Movie> movies;

    public MainModel(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}
