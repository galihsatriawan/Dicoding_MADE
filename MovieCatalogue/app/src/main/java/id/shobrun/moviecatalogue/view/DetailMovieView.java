package id.shobrun.moviecatalogue.view;

import id.shobrun.moviecatalogue.model.MovieModel;

public interface DetailMovieView extends RootView {
    void showDetailMovie(MovieModel model,int position);
}
