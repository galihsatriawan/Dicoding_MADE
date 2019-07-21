package id.shobrun.moviecatalogue.presenter;

import android.content.Context;

import id.shobrun.moviecatalogue.model.MovieModel;
import id.shobrun.moviecatalogue.view.MovieCatalogueView;

public class MovieCataloguePresenter {
    private MovieCatalogueView mMovieCatalogueView;
    private MovieModel mMovieModel;

    public MovieCataloguePresenter(MovieCatalogueView mMovieCatalogueView,Context ctx) {
        this.mMovieCatalogueView = mMovieCatalogueView;
        Context ctx1 = ctx;
        mMovieModel = new MovieModel(ctx);
    }
    public void loadMovieCatalogue(){
        mMovieCatalogueView.showListMovieCatalogue(mMovieModel);
    }
}
