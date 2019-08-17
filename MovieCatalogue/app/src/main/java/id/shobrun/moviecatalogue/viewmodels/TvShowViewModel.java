package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.data.TvShow;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TvShow>> tvShowsPopular;
    private MutableLiveData<ArrayList<TvShow>> tvShowsTrending;
    public MutableLiveData<ArrayList<TvShow>> getTvShowsPopular() {
        if(this.tvShowsPopular==null) return tvShowsPopular = new MutableLiveData<>();
        return tvShowsPopular;
    }

    public MutableLiveData<ArrayList<TvShow>> getTvShowsTrending() {
        if(this.tvShowsTrending==null) return tvShowsTrending = new MutableLiveData<>();
        return tvShowsTrending;
    }

    public void setTvShowsTrending(ArrayList<TvShow> tvShowsTrending) {
        if(this.tvShowsTrending==null) this.tvShowsTrending = new MutableLiveData<>();
        this.tvShowsTrending.postValue(tvShowsTrending);
    }

    public void setTvShowsPopular(ArrayList<TvShow> tvShowsPopular) {
        if(this.tvShowsPopular==null) this.tvShowsPopular= new MutableLiveData<>();
        this.tvShowsPopular.postValue(tvShowsPopular);
    }

}
