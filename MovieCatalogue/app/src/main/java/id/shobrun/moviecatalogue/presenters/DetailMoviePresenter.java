package id.shobrun.moviecatalogue.presenters;

import android.content.Context;

import id.shobrun.moviecatalogue.models.MovieModel;
import id.shobrun.moviecatalogue.views.DetailMovieView;

public class DetailMoviePresenter {
    private DetailMovieView mDetailMovieView;
    private Context ctx;

    public DetailMoviePresenter(Context ctx,DetailMovieView mDetailMovieView) {
        this.mDetailMovieView = mDetailMovieView;
        this.ctx = ctx;
    }
    public void loadDetailMovie(int id){
        MovieModel model = new MovieModel(ctx);
        int position = model.getMovieById(id);
        mDetailMovieView.showActionBar();
        mDetailMovieView.showDetailMovie(model,position);
    }
}
