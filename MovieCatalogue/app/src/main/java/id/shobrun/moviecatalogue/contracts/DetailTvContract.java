package id.shobrun.moviecatalogue.contracts;

import id.shobrun.moviecatalogue.models.data.TvShow;

public interface DetailTvContract {
    interface Model{

    }
    interface View extends RootView {
        void initUI();
        void showDetailTvShow(TvShow tvShow);
    }
    interface Presenter{
        void loadDetailTvShow(TvShow tvShow);
    }
}
