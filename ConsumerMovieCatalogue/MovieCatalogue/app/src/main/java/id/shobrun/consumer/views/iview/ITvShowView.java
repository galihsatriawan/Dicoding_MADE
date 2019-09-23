package id.shobrun.consumer.views.iview;

import java.util.ArrayList;

import id.shobrun.consumer.models.data.TvShow;

public interface ITvShowView extends IRootView {
    void showProgress();
    void hideProgress();
    void initViewModel();
    void initUI();
    void showListTvShowPopular(ArrayList<TvShow> tvShows);
    void showMessage(String message);
    void updateSearch(String str);
    interface TVShowItemView {
        void setPoster(String poster);
        void setNotification(int notif);
    }
}
