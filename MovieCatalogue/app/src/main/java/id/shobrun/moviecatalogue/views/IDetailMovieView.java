package id.shobrun.moviecatalogue.views;

import id.shobrun.moviecatalogue.models.data.Movie;

public interface IDetailMovieView extends IRootView {
    void initViewModel();
    void initUI();
    void showDetailMovie(Movie movie);
}
