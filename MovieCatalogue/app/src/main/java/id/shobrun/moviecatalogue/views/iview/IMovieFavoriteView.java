package id.shobrun.moviecatalogue.views.iview;

import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.models.data.Movie;

public interface IMovieFavoriteView {
    void initUI();
    void hideProgress();
    void showProgress();
    void showMessage(String msg);
    void showListMovie(ArrayList<Movie> movieList);
}
