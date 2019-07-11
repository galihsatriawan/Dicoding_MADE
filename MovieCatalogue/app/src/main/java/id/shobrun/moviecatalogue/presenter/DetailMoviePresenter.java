package id.shobrun.moviecatalogue.presenter;

import android.content.Context;

import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.model.DetailMovieModel;
import id.shobrun.moviecatalogue.view.DetailMovieView;

public class DetailMoviePresenter {
    private DetailMovieView mDetailMovieView;
    private Context ctx;

    public DetailMoviePresenter(Context ctx,DetailMovieView mDetailMovieView) {
        this.mDetailMovieView = mDetailMovieView;
        this.ctx = ctx;
    }
    public void loadDetailMovie(Movie movie){
        DetailMovieModel model = new DetailMovieModel(movie);
        mDetailMovieView.showDetailMovie(model);
    }
}
