package id.shobrun.moviecatalogue.views.iview;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.Movie;

public interface IMovieCatalogueView extends IRootView {
    void showProgress();
    void hideProgress();
    void initUI();
    void initViewModel();
    void showMessage(String str);
    void showListMovieCatalogue(ArrayList<Movie> movies);
    void updateSearch(String str);
}
