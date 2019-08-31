package id.shobrun.moviecatalogue.views.fragment;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.Movie;

public interface IMovieCatalogueView {
    void showProgress();
    void hideProgress();
    void initUI();
    void showMessage(String str);
    void showListMovieCatalogue(ArrayList<Movie> movies);
}
