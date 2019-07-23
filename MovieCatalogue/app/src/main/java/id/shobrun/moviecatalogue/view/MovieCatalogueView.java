package id.shobrun.moviecatalogue.view;

import android.view.View;

import id.shobrun.moviecatalogue.model.MovieModel;

public interface MovieCatalogueView {
    void showListMovieCatalogue(MovieModel model);
    void showDetailMovie();
}
