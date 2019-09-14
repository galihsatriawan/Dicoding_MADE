package id.shobrun.moviecatalogue.repositories;

import android.content.Context;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.local.ConsumerMovieLocalData;

public class ConsumerMovieRepository implements IConsumerMovieDataSource.DBSource {
    ConsumerMovieLocalData localData;
    private static ConsumerMovieRepository INSTANCE;
    public ConsumerMovieRepository(Context context){
        localData = new ConsumerMovieLocalData(context);
    }

    public static ConsumerMovieRepository getInstance(Context context) {
        if(INSTANCE==null){
            synchronized (ConsumerMovieRepository.class){
                if(INSTANCE==null){
                    INSTANCE = new ConsumerMovieRepository(context);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void updateMovieLocal(Movie movie, UpdateDataCallback callback) {
        localData.updateMovieLocal(movie,callback);
    }

    @Override
    public void deleteMovieLocal(Movie movie, UpdateDataCallback callback) {
        localData.deleteMovieLocal(movie,callback);
    }

    @Override
    public void insertMovieLocal(Movie movie, UpdateDataCallback callback) {
        localData.insertMovieLocal(movie,callback);
    }

    @Override
    public void getWishListMoviesLocal(String tags, LoadDataCallback callback) {
        localData.getWishListMoviesLocal(tags,callback);
    }

    @Override
    public ArrayList<Movie> getWishListMoviesLocalSync(String tags) {
        return localData.getWishListMoviesLocalSync(tags);
    }

    @Override
    public void getMovieByIdLocal(int id, LoadDataCallback callback) {
        localData.getMovieByIdLocal(id,callback);
    }
}
