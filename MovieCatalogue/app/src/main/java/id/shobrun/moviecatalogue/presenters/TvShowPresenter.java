package id.shobrun.moviecatalogue.presenters;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.data.TvShow;
import id.shobrun.moviecatalogue.component.response.TvShowListResponse;
import id.shobrun.moviecatalogue.contracts.TvShowContract;
import id.shobrun.moviecatalogue.models.TvShowModel;
import id.shobrun.moviecatalogue.viewmodels.TvShowViewModel;
import retrofit2.Response;


public class TvShowPresenter implements TvShowContract.Presenter {
    private final String TAG = this.getClass().getSimpleName();
    private TvShowContract.View mView;
    private TvShowContract.Model mTvModel;
    public TvShowContract.Model.OnFinishedListener listenerPopular;
    public TvShowContract.Model.OnFinishedListener listenerTrending;
    private Context ctx;
    public TvShowPresenter(TvShowContract.View mView, Context ctx) {
        this.mView = mView;
        this.ctx = ctx;
        mTvModel = new TvShowModel(ctx);
        mView.initUI();
    }
    @Override
    public void loadTvShowPopular(final TvShowViewModel vm) {
        listenerPopular = new TvShowContract.Model.OnFinishedListener() {
            @Override
            public void onSuccess(Response<TvShowListResponse> response) {
                vm.setTvShowsPopular(response.body().getResults());
                mView.showListTvShowPopular(response.body().getResults());
                mView.hideProgress();
            }

            @Override
            public void onRefresh(ArrayList<TvShow> tvShows) {
                mView.showListTvShowPopular(tvShows);
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
        if(vm.getTvShowsPopular().getValue()==null){
            mView.showProgress();
            mTvModel.getAllTvShowPopular(listenerPopular);
        }

    }

    @Override
    public void loadTvShowTrending(final TvShowViewModel vm) {
        listenerTrending = new TvShowContract.Model.OnFinishedListener() {
            @Override
            public void onSuccess(Response<TvShowListResponse> response) {
                vm.setTvShowsTrending(response.body().getResults());
                mView.showListTvShowTrending(response.body().getResults());
                mView.hideProgress();
            }

            @Override
            public void onRefresh(ArrayList<TvShow> tvShows) {
                mView.showListTvShowTrending(tvShows);
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
        if(vm.getTvShowsTrending().getValue()==null){
            mView.showProgress();
            mTvModel.getAllTvShowTrending(listenerTrending);
        }
    }
}
