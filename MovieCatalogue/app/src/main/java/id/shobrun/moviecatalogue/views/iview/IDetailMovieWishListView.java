package id.shobrun.moviecatalogue.views.iview;

import id.shobrun.moviecatalogue.models.data.Movie;

public interface IDetailMovieWishListView extends IRootView{
    void initViewModel();
    void initUI();
    void showDetailMovie(Movie movie);
    void setIconWishList(int res);
    void showMessage(String str);
    void showMessageToast(String str);
    void hideProgress();
    void showProgress();
}
