package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.repositories.ITvShowDataSource;
import id.shobrun.moviecatalogue.repositories.TvShowRepository;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.iview.ITvShowFavoriteView;

public class TvShowFavoriteViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TvShow>> tvShows = new MutableLiveData<>();
    private TvShowRepository repository;
    private ITvShowFavoriteView mView;

    public void setAppView(Context context, ITvShowFavoriteView view){
        Context context1 = context;
        mView = view;
        this.repository = TvShowRepository.getInstance(context);
    }


    private void setTvShows(ArrayList<TvShow> tvShows) {
        this.tvShows.setValue(tvShows);
    }

    public LiveData<ArrayList<TvShow>> getTvShows() {
        return tvShows;
    }
    public void loadFavoriteTvShows(){
        repository.getFavoriteTvShowLocal(Constants.TAGS_FAVORITE, new ITvShowDataSource.DBSource.LoadDataCallback() {
            @Override
            public void onPreLoad() {
                mView.showProgress();
            }

            @Override
            public <T> void onLoadSuccess(T res) {
                ArrayList<TvShow> tvShows= (ArrayList<TvShow>) res;
                TvShowFavoriteViewModel.this.setTvShows(tvShows);
                mView.hideProgress();
                Log.d(this.getClass().getSimpleName(), "onLoadSuccess: ");
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
