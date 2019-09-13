package id.shobrun.moviecatalogue.repositories;

import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.models.data.Movie;
import retrofit2.Response;

public interface IMoviesDataSource {
    interface ApiSource{
        List<Movie> getMoviesData(OnFinishedListener listener);
        List<Movie> getReleaseMovie(OnFinishedListener listener);
        List<Movie> getSearchMoviesData(String str,OnFinishedListener listener);

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
        void updateMovieLocal(Movie movie,UpdateDataCallback callback);
        void deleteMovieLocal(Movie movie,UpdateDataCallback callback);
        void insertMovieLocal(Movie movie,UpdateDataCallback callback);
        void getLikeMoviesLocal(String tags,LoadDataCallback callback);
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
