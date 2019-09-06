package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.api.response.TvShowListResponse;
import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.repositories.ITvShowDataSource;
import id.shobrun.moviecatalogue.repositories.TvShowRepository;
import id.shobrun.moviecatalogue.views.iview.ITvShowView;
import retrofit2.Response;

public class TvShowViewModel extends ViewModel {
    private Context context;
    private ITvShowView mView;
    private TvShowRepository mRepository;
    private MutableLiveData<ArrayList<TvShow>> tvShowsPopular;

    public void setAppView(Context context,ITvShowView view){
        this.context = context;
        this.mView = view;
        this.mRepository = new TvShowRepository(context);
    }
    public void loadSearchTvShow(String str){
        mView.showProgress();
        mRepository.getSearchTvShow(str, new ITvShowDataSource.ApiSource.OnFinishedListener() {
            @Override
            public void onFinished(Response<TvShowListResponse> response) {
                mView.hideProgress();
                setTvShowsPopular(response.body().getResults());
            }

            @Override
            public void onRefresh(ArrayList<TvShow> tvShow) {
                mView.hideProgress();
                setTvShowsPopular(tvShow);
            }

            @Override
            public void onError(Response<TvShowListResponse> response) {
                mView.hideProgress();
                mView.showMessage(context.getString(R.string.communication_error));
            }

            @Override
            public void onFailure(Throwable t) {
                mView.hideProgress();
                mView.showMessage(context.getString(R.string.communication_error));
            }
        });
    }
    public void loadTvShowPopular(){
        mView.showProgress();
        mRepository.getAllTvShowPopular(new ITvShowDataSource.ApiSource.OnFinishedListener() {
            @Override
            public void onFinished(Response<TvShowListResponse> response) {
                mView.hideProgress();
                ArrayList<TvShow> result = response.body().getResults();
                setTvShowsPopular(result);
                if(result.size()==0){
                    mView.showMessage(context.getResources().getString(R.string.empty_tv));
                }
            }

            @Override
            public void onRefresh(ArrayList<TvShow> tvShow) {
                mView.hideProgress();
                if(tvShow.size()==0){
                    mView.showMessage(context.getResources().getString(R.string.empty_tv));
                }
            }

            @Override
            public void onError(Response<TvShowListResponse> response) {
                mView.hideProgress();
                mView.showMessage(context.getString(R.string.communication_error));

            }

            @Override
            public void onFailure(Throwable t) {
                mView.hideProgress();
                mView.showMessage(context.getString(R.string.communication_error));

            }
        });
    }

    public MutableLiveData<ArrayList<TvShow>> getTvShowsPopular() {
        if(this.tvShowsPopular==null) return tvShowsPopular = new MutableLiveData<>();
        return tvShowsPopular;
    }
    private void setTvShowsPopular(ArrayList<TvShow> tvShowsPopular) {
        if(this.tvShowsPopular==null) this.tvShowsPopular= new MutableLiveData<>();
        this.tvShowsPopular.postValue(tvShowsPopular);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
