package id.shobrun.moviecatalogue.model;

import id.shobrun.moviecatalogue.data.Movie;

public class DetailMovieModel {
    private Movie movie;

    public DetailMovieModel(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
