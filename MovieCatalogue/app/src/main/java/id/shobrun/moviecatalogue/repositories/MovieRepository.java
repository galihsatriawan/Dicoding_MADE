package id.shobrun.moviecatalogue.repositories;

import android.app.Application;

import java.util.List;

import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.local.MovieLocalData;
import id.shobrun.moviecatalogue.repositories.remote.MovieRemoteData;

public class MovieRepository implements IMoviesDataSource.ApiSource,IMoviesDataSource.DBSource{
    private MovieRemoteData remoteData;
    private MovieLocalData localData;
    private static MovieRepository INSTANCE;
    public static MovieRepository getInstance(Application application){
        if(INSTANCE == null){
            synchronized (MovieRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new MovieRepository(application);
                }
            }
        }
        return INSTANCE;
    }
    public MovieRepository(Application application){
        this.localData = new MovieLocalData(application);
        this.remoteData = new MovieRemoteData(application);
    }

    @Override
    public List<Movie> getMoviesData(OnFinishedListener listener) {
        return remoteData.getMoviesData(listener);
    }

    @Override
    public Movie getMovie(int position) {
        return remoteData.getMovie(position);
    }

    @Override
    public int getMovieById(int id) {
        return remoteData.getMovieById(id);
    }

    @Override
    public int getMovieCount() {
        return remoteData.getMovieCount();
    }

    @Override
    public void deleteMovieLocal(Movie movie, UpdateDataCallback callback) {
        localData.insertMovieLocal(movie,callback);
    }

    @Override
    public void insertMovieLocal(Movie movie, UpdateDataCallback callback) {
        localData.insertMovieLocal(movie,callback);
    }

    @Override
    public void getLikeMoviesLocal(String tags, LoadDataCallback callback) {
        localData.getLikeMoviesLocal(tags,callback);
    }
}
