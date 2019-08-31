package id.shobrun.moviecatalogue.repositories;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.models.data.Movie;
import retrofit2.Response;

public interface IMoviesDataSource {
    interface ApiSource{
        List<Movie> getMoviesData(OnFinishedListener listener);
        Movie getMovie(int position);
        int getMovieById(int id);
        int getMovieCount();
        interface OnFinishedListener{
            void onFinished(Response<MovieListResponse> response);
            void onRefresh(ArrayList<Movie> movies);
            void onError(Response<MovieListResponse> response);
            void onFailure(Throwable t);
        }
    }
    interface DBSource{

        void deleteMovieLocal(Movie movie,UpdateDataCallback callback);
        void insertMovieLocal(Movie movie,UpdateDataCallback callback);
        void getLikeMoviesLocal(String tags,LoadDataCallback callback);
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
