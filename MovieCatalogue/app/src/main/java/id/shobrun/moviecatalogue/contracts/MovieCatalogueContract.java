package id.shobrun.moviecatalogue.contracts;

import android.view.View;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.adapter.MovieViewHolder;
import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.models.MovieModel;

public interface MovieCatalogueContract {

    interface Model {
        interface OnFinishedListener{
            void onFinished(ArrayList<Movie> movies);
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
        void showListMovieCatalogue(ArrayList<Movie> movies);
        void showDetailMovie(Movie movie);
        void onResponseFailure();
    }
    interface Presenter{
        void loadMovieCatalogue();
    }


}
