package id.shobrun.moviecatalogue.views.iview;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.TvShow;

public interface ITvShowFavoriteView {
    void initUI();
    void hideProgress();
    void showProgress();
    void showMessage(String msg);
    void showListTvShow(ArrayList<TvShow> tvShows);
}
