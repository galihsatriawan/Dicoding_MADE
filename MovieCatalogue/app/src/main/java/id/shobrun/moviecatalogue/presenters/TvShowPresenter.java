package id.shobrun.moviecatalogue.presenters;

import android.content.Context;

import id.shobrun.moviecatalogue.models.MovieModel;
import id.shobrun.moviecatalogue.views.TvShowView;

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
