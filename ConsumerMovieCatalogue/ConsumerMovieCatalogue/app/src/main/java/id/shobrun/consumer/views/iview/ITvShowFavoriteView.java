package id.shobrun.consumer.views.iview;

import java.util.ArrayList;

import id.shobrun.consumer.models.data.TvShow;

public interface ITvShowFavoriteView {
    void initUI();
    void initViewModel();
    void hideProgress();
    void showProgress();
    void showMessage(String msg);
    void showListTvShow(ArrayList<TvShow> tvShows);
}
