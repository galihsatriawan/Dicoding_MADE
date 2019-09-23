package id.shobrun.consumer.views.iview;

import java.util.ArrayList;

import id.shobrun.consumer.models.data.Movie;

public interface IConsumerWishlistMovieView {
    void initUI();
    void initViewModel();
    void hideProgress();
    void showProgress();
    void showMessage(String msg);
    void showListMovie(ArrayList<Movie> movieList);
}
