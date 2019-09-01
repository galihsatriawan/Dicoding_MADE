package id.shobrun.moviecatalogue.viewmodels;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.views.iview.IMovieCatalogueView;
import retrofit2.Response;


public class MovieCatalogueViewModel extends ViewModel {
    private  MovieRepository mRepository ;
    private Context context;
    private IMovieCatalogueView mView;

    public void setAppView(Context context,IMovieCatalogueView view){
        this.context = context;
        mRepository = MovieRepository.getInstance(context);
        mView = view;
    }
    public void loadAllMovie(){
        mView.showProgress();
        mRepository.getMoviesData(new IMoviesDataSource.ApiSource.OnFinishedListener() {
            @Override
            public void onFinished(Response<MovieListResponse> response) {
                setMovies(response.body().getResults());
                mView.hideProgress();
            }

            @Override
            public void onRefresh(ArrayList<Movie> movies) {
                setMovies(movies);
                mView.hideProgress();
            }

            @Override
            public void onError(Response<MovieListResponse> response) {
                mView.showMessage(context.getString(R.string.communication_error));
                mView.hideProgress();
            }

            @Override
            public void onFailure(Throwable t) {
                mView.showMessage(context.getString(R.string.communication_error));
                mView.hideProgress();
            }
        });
    }
    /**
     * Keep observe data
     */
    private MutableLiveData<ArrayList<Movie>> movies;

    public LiveData<ArrayList<Movie>> getMovies(){
        if(movies==null) return movies = new MutableLiveData<>();
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        if(this.movies==null){
            this.movies = new MutableLiveData<>();
        }
        this.movies.postValue(movies);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
