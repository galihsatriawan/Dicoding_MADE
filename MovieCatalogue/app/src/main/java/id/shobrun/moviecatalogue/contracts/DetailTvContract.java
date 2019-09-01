package id.shobrun.moviecatalogue.contracts;

import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.views.iview.IRootView;

public interface DetailTvContract {
    interface Model{

    }
    interface ViewI extends IRootView {
        void initUI();
        void showDetailTvShow(TvShow tvShow);
    }
    interface Presenter{
        void loadDetailTvShow(TvShow tvShow);
    }
}
