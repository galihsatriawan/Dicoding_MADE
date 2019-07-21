package id.shobrun.moviecatalogue.presenter;

import android.content.Context;

import id.shobrun.moviecatalogue.model.MovieModel;
import id.shobrun.moviecatalogue.view.TvShowView;

public class TvShowPresenter {
    private TvShowView mView;
    private MovieModel mMovieModel;
    private Context ctx;
    public TvShowPresenter(TvShowView mView, Context ctx) {
        this.mView = mView;
        this.ctx = ctx;
        mMovieModel = new MovieModel(ctx);
    }
    public void loadTVShow(){
        mView.showListTvShow(mMovieModel);
    }
}
