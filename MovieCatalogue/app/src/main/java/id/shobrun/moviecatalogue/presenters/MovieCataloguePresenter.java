package id.shobrun.moviecatalogue.presenters;

import android.content.Context;

import id.shobrun.moviecatalogue.models.MovieModel;
import id.shobrun.moviecatalogue.views.MovieCatalogueView;

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
