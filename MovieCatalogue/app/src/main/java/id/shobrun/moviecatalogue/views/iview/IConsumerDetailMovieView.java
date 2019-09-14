package id.shobrun.moviecatalogue.views.iview;

import id.shobrun.moviecatalogue.models.data.Movie;

public interface IConsumerDetailMovieView {
    void initViewModel();
    void initUI();
    void showDetailMovie(Movie movie);
    void setIconWishlist(int res);
    void showMessage(String str);
    void showMessageToast(String str);
    void hideProgress();
    void showProgress();
}
