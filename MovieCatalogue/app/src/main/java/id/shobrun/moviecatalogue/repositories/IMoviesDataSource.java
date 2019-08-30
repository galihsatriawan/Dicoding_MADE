package id.shobrun.moviecatalogue.repositories;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.models.data.Movie;

public interface IMoviesDataSource {
    void getMoviesData(GetMoviesDataCallback callback);
    interface GetMoviesDataCallback{
        void onDataLoaded(ArrayList<Movie> movies);
        void onNotAvailable();
        void onError();
    }
    interface LoadDataCallback{
        void onPreLoad();
        <T> void onLoadSuccess(T res);
    }
    interface UpdateDataCallback{
        void onPreExecute();
        <T> void onPostExecute(T res);
    }
}
