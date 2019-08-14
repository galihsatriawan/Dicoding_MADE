package id.shobrun.moviecatalogue.presenters;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.component.response.MovieListResponse;
import id.shobrun.moviecatalogue.contracts.MovieCatalogueContract;
import id.shobrun.moviecatalogue.models.MovieModel;
import retrofit2.Response;

public class MovieCataloguePresenter implements  MovieCatalogueContract.Presenter,MovieCatalogueContract.Model.OnFinishedListener{
    private final String TAG = this.getClass().getSimpleName();
    private MovieCatalogueContract.View mMovieCatalogueView;
    private MovieModel mMovieModel;
    private Context ctx;
    public MovieCataloguePresenter(MovieCatalogueContract.View mMovieCatalogueView,Context ctx) {
        this.mMovieCatalogueView = mMovieCatalogueView;
        this.ctx = ctx;
        mMovieCatalogueView.initUI();
        mMovieModel = new MovieModel(ctx);
    }


    @Override
    public void loadMovieCatalogue() {
        /*
        Contact Model to load From Server
         */
        mMovieCatalogueView.showProgress();
        mMovieModel.getAllMovies(this );
    }

    @Override
    public void onFinished(Response<MovieListResponse> response) {
        mMovieCatalogueView.hideProgress();
        mMovieCatalogueView.showListMovieCatalogue(response.body().getResults());
    }

    @Override
    public void onError(Response<MovieListResponse> response) {
        Log.e(TAG, "onError: "+response.errorBody());
        mMovieCatalogueView.hideProgress();
        mMovieCatalogueView.showMessage(this.ctx.getString(R.string.communication_error));
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        mMovieCatalogueView.showMessage(this.ctx.getString(R.string.communication_error));
    }

}
