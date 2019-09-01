package id.shobrun.moviecatalogue.views.iview;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.TvShow;

public interface ITvShowView extends IRootView {
    void showProgress();
    void hideProgress();
    void initUI();
    void showListTvShowPopular(ArrayList<TvShow> tvShows);
    void showListTvShowTrending(ArrayList<TvShow> tvShows);
    void showMessage(String message);
    interface TVShowItemView {
        void setPoster(String poster);
        void setNotification(int notif);
    }
}
