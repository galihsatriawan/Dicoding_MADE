package id.shobrun.moviecatalogue.viewmodels;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.presenters.TvShowPresenter;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.views.fragment.IMovieCatalogueView;
import retrofit2.Response;


public class MovieCatalogueViewModel extends ViewModel {
    private  MovieRepository mRepository ;
    private Application application;
    private IMovieCatalogueView mView;
    MovieCatalogueViewModel(Application application,IMovieCatalogueView view){
        this.application = application;
        mRepository = MovieRepository.getInstance(application);
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
                mView.showMessage(application.getApplicationContext().getString(R.string.communication_error));
                mView.hideProgress();
            }

            @Override
            public void onFailure(Throwable t) {
                mView.showMessage(application.getApplicationContext().getString(R.string.communication_error));
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
