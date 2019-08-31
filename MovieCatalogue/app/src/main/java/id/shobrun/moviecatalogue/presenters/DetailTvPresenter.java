package id.shobrun.moviecatalogue.presenters;

import android.content.Context;

import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.contracts.DetailTvContract;

public class DetailTvPresenter implements DetailTvContract.Presenter {
    private DetailTvContract.ViewI mView;
    private Context ctx;

    public DetailTvPresenter(Context ctx, DetailTvContract.ViewI mView) {
        this.mView = mView;
        this.ctx = ctx;
        mView.initUI();
    }

    @Override
    public void loadDetailTvShow(TvShow tvShow) {
        mView.showDetailTvShow(tvShow);
    }
}
