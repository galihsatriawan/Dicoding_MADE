package id.shobrun.moviecatalogue.presenters;

import android.content.Context;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.contracts.MovieCatalogueContract;
import id.shobrun.moviecatalogue.models.MovieModel;

public class MovieCataloguePresenter implements  MovieCatalogueContract.Presenter,MovieCatalogueContract.Model.OnFinishedListener{
    private MovieCatalogueContract.View mMovieCatalogueView;
    private MovieModel mMovieModel;
    private Context ctx;
    public MovieCataloguePresenter(MovieCatalogueContract.View mMovieCatalogueView,Context ctx) {
        this.mMovieCatalogueView = mMovieCatalogueView;
        this.ctx = ctx;
        mMovieModel = new MovieModel(ctx);
    }


    @Override
    public void loadMovieCatalogue() {
        /*
        Contact Model to load From Server
         */
    }

    @Override
    public void onFinished(ArrayList<Movie> movies) {
        mMovieCatalogueView.showListMovieCatalogue(movies);
    }

    @Override
    public void onFailure(Throwable t) {

    }

}
