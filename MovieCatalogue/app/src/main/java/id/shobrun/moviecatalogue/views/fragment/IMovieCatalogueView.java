package id.shobrun.moviecatalogue.views.fragment;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.views.IRootView;

public interface IMovieCatalogueView extends IRootView {
    void showProgress();
    void hideProgress();
    void initUI();
    void showMessage(String str);
    void showListMovieCatalogue(ArrayList<Movie> movies);
}
