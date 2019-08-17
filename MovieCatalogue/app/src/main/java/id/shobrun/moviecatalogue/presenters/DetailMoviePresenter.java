package id.shobrun.moviecatalogue.presenters;

import android.content.Context;

import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.contracts.DetailMovieContract;

public class DetailMoviePresenter implements DetailMovieContract.Presenter {
    private DetailMovieContract.View mDetailMovieView;
    private Context ctx;

    public DetailMoviePresenter(Context ctx,DetailMovieContract.View mDetailMovieView) {
        this.mDetailMovieView = mDetailMovieView;
        this.ctx = ctx;
        mDetailMovieView.initUI();
    }
    public void loadDetailMovie(Movie movie){
        mDetailMovieView.showActionBar();
        mDetailMovieView.showDetailMovie(movie);
    }
}
