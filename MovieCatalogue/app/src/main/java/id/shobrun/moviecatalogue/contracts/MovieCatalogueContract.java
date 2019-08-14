package id.shobrun.moviecatalogue.contracts;

import android.view.View;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.adapter.MovieViewHolder;
import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.component.response.MovieListResponse;
import id.shobrun.moviecatalogue.models.MovieModel;
import retrofit2.Response;

public interface MovieCatalogueContract {

    interface Model {
        interface OnFinishedListener{
            void onFinished(Response<MovieListResponse> response);
            void onError(Response<MovieListResponse> response);
            void onFailure(Throwable t);
        }
        ArrayList<Movie> getAllMovies(OnFinishedListener onFinishedListener);
        Movie getMovie(int position);
        int getMovieById(int id);
        int getMovieCount();
    }
    interface View{
        void showProgress();
        void hideProgress();
        void initUI();
        void showMessage(String str);
        void showListMovieCatalogue(ArrayList<Movie> movies);

    }
    interface Presenter{
        void loadMovieCatalogue();
    }


}
