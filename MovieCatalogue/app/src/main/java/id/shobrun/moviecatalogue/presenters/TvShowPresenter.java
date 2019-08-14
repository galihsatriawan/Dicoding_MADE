package id.shobrun.moviecatalogue.presenters;

import android.content.Context;
import android.util.Log;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.response.TvShowListResponse;
import id.shobrun.moviecatalogue.contracts.TvShowContract;
import id.shobrun.moviecatalogue.models.TvShowModel;
import retrofit2.Response;


public class TvShowPresenter implements TvShowContract.Presenter {
    private final String TAG = this.getClass().getSimpleName();
    private TvShowContract.View mView;
    private TvShowContract.Model mTvModel;
    private TvShowContract.Model.OnFinishedListener listenerPopular;
    private TvShowContract.Model.OnFinishedListener listenerTrending;
    private Context ctx;
    public TvShowPresenter(TvShowContract.View mView, Context ctx) {
        this.mView = mView;
        this.ctx = ctx;
        mTvModel = new TvShowModel(ctx);
        mView.initUI();
    }
    @Override
    public void loadTvShowPopular() {
        listenerPopular = new TvShowContract.Model.OnFinishedListener() {
            @Override
            public void onSuccess(Response<TvShowListResponse> response) {
                mView.showListTvShowPopular(response.body().getResults());
                mView.hideProgress();
            }

            @Override
            public void onError(Response<TvShowListResponse> response) {
                mView.showMessage(TvShowPresenter.this.ctx.getString(R.string.communication_error));
                mView.hideProgress();
            }

            @Override
            public void onFailure(Throwable t) {
                mView.showMessage(TvShowPresenter.this.ctx.getString(R.string.communication_error));
                mView.hideProgress();
            }
        };
        mView.showProgress();
        mTvModel.getAllTvShowPopular(listenerPopular);
    }

    @Override
    public void loadTvShowTrending() {
        listenerTrending = new TvShowContract.Model.OnFinishedListener() {
            @Override
            public void onSuccess(Response<TvShowListResponse> response) {
                mView.showListTvShowTrending(response.body().getResults());
                mView.hideProgress();
            }

            @Override
            public void onError(Response<TvShowListResponse> response) {
                Log.e(TAG, "onError: "+response.errorBody());
                mView.showMessage(TvShowPresenter.this.ctx.getString(R.string.communication_error));
            }

            @Override
            public void onFailure(Throwable t) {
                mView.showMessage(TvShowPresenter.this.ctx.getString(R.string.communication_error));
            }
        };
        mView.showProgress();
        mTvModel.getAllTvShowTrending(listenerTrending);

    }
}
