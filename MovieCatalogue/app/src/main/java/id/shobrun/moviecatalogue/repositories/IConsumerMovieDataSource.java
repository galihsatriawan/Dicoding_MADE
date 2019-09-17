package id.shobrun.moviecatalogue.repositories;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.Movie;

public interface IConsumerMovieDataSource {
    interface DBSource{
        void updateMovieLocal(Movie movie, UpdateDataCallback callback);
        void deleteMovieLocal(Movie movie,UpdateDataCallback callback);
        void deleteMovieByIdLocal(int id,UpdateDataCallback callback);
        void insertMovieLocal(Movie movie,UpdateDataCallback callback);
        void getMovieByTags(String tags, LoadDataCallback callback);
        ArrayList<Movie> getWishListMoviesLocalSync(String tags);
        void getMovieByIdLocal(int id,LoadDataCallback callback);
        interface LoadDataCallback{
            void onPreLoad();
            <T> void onLoadSuccess(T res);
        }
        interface UpdateDataCallback{
            void onPreExecute();
            <T> void onPostExecute(T res);
        }
    }
}
