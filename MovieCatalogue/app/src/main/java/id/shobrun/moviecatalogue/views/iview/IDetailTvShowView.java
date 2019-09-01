package id.shobrun.moviecatalogue.views.iview;

import id.shobrun.moviecatalogue.models.data.TvShow;

public interface IDetailTvShowView extends IRootView {
    void initUI();
    void setIconFavorite(int res);
    void showDetailTvShow(TvShow tvShow);
    void showMessage(String str);
}
