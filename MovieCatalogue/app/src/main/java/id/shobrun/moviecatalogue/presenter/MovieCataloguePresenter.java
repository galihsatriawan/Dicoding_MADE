package id.shobrun.moviecatalogue.presenter;

import android.content.Context;

import id.shobrun.moviecatalogue.model.MovieModel;
import id.shobrun.moviecatalogue.view.MovieCatalogueView;

public class MovieCataloguePresenter {
    private MovieCatalogueView mMovieCatalogueView;
    private MovieModel mMovieModel;
    private Context ctx;
    public MovieCataloguePresenter(MovieCatalogueView mMovieCatalogueView,Context ctx) {
        this.mMovieCatalogueView = mMovieCatalogueView;
        this.ctx = ctx;
        mMovieModel = new MovieModel(ctx);
    }
    public void loadMovieCatalogue(){
        mMovieCatalogueView.showListMovieCatalogue(mMovieModel);
    }
}
