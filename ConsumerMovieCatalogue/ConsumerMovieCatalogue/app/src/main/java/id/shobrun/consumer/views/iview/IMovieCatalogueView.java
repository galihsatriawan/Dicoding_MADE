package id.shobrun.consumer.views.iview;

import java.util.ArrayList;

import id.shobrun.consumer.models.data.Movie;

public interface IMovieCatalogueView extends IRootView {
    void showProgress();
    void hideProgress();
    void initUI();
    void initViewModel();
    void showMessage(String str);
    void showMessageToast(String str);
    void showListMovieCatalogue(ArrayList<Movie> movies);
    void updateSearch(String str);
}
