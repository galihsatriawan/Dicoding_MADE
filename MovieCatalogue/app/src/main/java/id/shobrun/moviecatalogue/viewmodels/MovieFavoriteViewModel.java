package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.iview.IMovieFavoriteView;

public class MovieFavoriteViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> movies = new MutableLiveData<>();
    private MovieRepository repository;
    private IMovieFavoriteView mView;

    public void setAppView(Context context, IMovieFavoriteView view){
        Context context1 = context;
        mView = view;
        this.repository = MovieRepository.getInstance(context);
    }


    private void setMovies(ArrayList<Movie> movies) {
        this.movies.setValue(movies);
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return movies;
    }
    public void loadFavoriteMovie(){
        repository.getLikeMoviesLocal(Constants.TAGS_FAVORITE, new IMoviesDataSource.DBSource.LoadDataCallback() {
            @Override
            public void onPreLoad() {
                mView.showProgress();
            }

            @Override
            public <T> void onLoadSuccess(T res) {
                ArrayList<Movie> movies = (ArrayList<Movie>) res;
                MovieFavoriteViewModel.this.setMovies(movies);
                mView.hideProgress();
                Log.d(this.getClass().getSimpleName(), "onLoadSuccess: ");
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
