package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.iview.IMovieFavoriteView;

public class MovieFavoriteViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> movies = new MutableLiveData<>();
    private MovieRepository repository;
    private IMovieFavoriteView mView;
    Context context;
    public void setAppView(Context context, IMovieFavoriteView view){
        this.context = context;
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
                mView.hideProgress();
                MovieFavoriteViewModel.this.setMovies(movies);
                if(movies.size()==0){
                    mView.showMessage(context.getResources().getString(R.string.empty_movie));
                }
                Log.d(this.getClass().getSimpleName(), "onLoadSuccess: ");
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
