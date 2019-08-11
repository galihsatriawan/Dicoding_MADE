package id.shobrun.moviecatalogue.views;

import id.shobrun.moviecatalogue.models.MovieModel;

public interface DetailMovieView extends RootView {
    void showDetailMovie(MovieModel model,int position);
}
