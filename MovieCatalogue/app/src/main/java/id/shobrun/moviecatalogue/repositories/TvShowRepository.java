package id.shobrun.moviecatalogue.repositories;

import android.content.Context;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.repositories.local.MovieLocalData;
import id.shobrun.moviecatalogue.repositories.local.TvShowLocalData;
import id.shobrun.moviecatalogue.repositories.remote.TvShowRemoteData;

public class TvShowRepository implements ITvShowDataSource.DBSource,ITvShowDataSource.ApiSource {
    private TvShowLocalData localData;
    private TvShowRemoteData remoteData;
    private Context context;
    private static TvShowRepository INSTANCE ;

    public static TvShowRepository getInstance(Context context) {
        if(INSTANCE==null){
            synchronized (TvShowRepository.class){
                if (INSTANCE==null) INSTANCE = new TvShowRepository(context);
            }
        }
        return INSTANCE;
    }

    public TvShowRepository(Context context){
        this.context = context;
        localData = new TvShowLocalData(context);
        remoteData = new TvShowRemoteData(context);
    }

    @Override
    public ArrayList<TvShow> getAllTvShowPopular(OnFinishedListener onFinishedListener) {
        return remoteData.getAllTvShowPopular(onFinishedListener);
    }

    @Override
    public ArrayList<TvShow> getAllTvShowTrending(OnFinishedListener onFinishedListener) {
        return remoteData.getAllTvShowTrending(onFinishedListener);
    }

    @Override
    public int getTvShow(int position) {
        return remoteData.getTvShow(position);
    }

    @Override
    public int getTvShowCount() {
        return remoteData.getTvShowCount();
    }

    @Override
    public void updateTvShowLocal(TvShow tvShow, UpdateDataCallback callback) {
        localData.updateTvShowLocal(tvShow,callback);
    }

    @Override
    public void deleteTvShowLocal(TvShow tvShow, UpdateDataCallback callback) {
        localData.deleteTvShowLocal(tvShow,callback);
    }

    @Override
    public void insertTvShowLocal(TvShow tvShow, UpdateDataCallback callback) {
        localData.insertTvShowLocal(tvShow,callback);
    }

    @Override
    public void getFavoriteTvShowLocal(String tags, LoadDataCallback callback) {
        localData.getFavoriteTvShowLocal(tags,callback);
    }

    @Override
    public void getTvShowByIdLocal(int id, LoadDataCallback callback) {
        localData.getTvShowByIdLocal(id,callback);
    }
}
