package id.shobrun.consumer.views.iview;

import id.shobrun.consumer.models.data.TvShow;

public interface IDetailTvShowView extends IRootView {
    void initUI();
    void setIconFavorite(int res);
    void showDetailTvShow(TvShow tvShow);
    void showMessage(String str);
}
