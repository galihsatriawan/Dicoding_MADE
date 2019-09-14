package id.shobrun.moviecatalogue.repositories.local;

import android.content.Context;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IConsumerMovieDataSource;

public class ConsumerMovieLocalData implements IConsumerMovieDataSource.DBSource {
    private Context context;
    public ConsumerMovieLocalData(Context context){
        this.context = context;
    }
    @Override
    public void updateMovieLocal(Movie movie, UpdateDataCallback callback) {

    }

    @Override
    public void deleteMovieLocal(Movie movie, UpdateDataCallback callback) {

    }

    @Override
    public void insertMovieLocal(Movie movie, UpdateDataCallback callback) {

    }

    @Override
    public void getWishListMoviesLocal(String tags, LoadDataCallback callback) {

    }

    @Override
    public ArrayList<Movie> getWishListMoviesLocalSync(String tags) {
        return null;
    }

    @Override
    public void getMovieByIdLocal(int id, LoadDataCallback callback) {

    }
}
