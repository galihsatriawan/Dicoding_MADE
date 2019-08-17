package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.component.response.MovieListResponse;

import id.shobrun.moviecatalogue.presenters.MovieCataloguePresenter;
import id.shobrun.moviecatalogue.presenters.MovieRecyclerPresenter;
import retrofit2.Response;

public class MovieCatalogueViewModel extends ViewModel {
    /**
     * Keep Presenter and observe data
     */
    private MovieCataloguePresenter movieCataloguePresenter;
    private MovieRecyclerPresenter recyclerPresenter;

    MutableLiveData<ArrayList<Movie>> movies = new MutableLiveData<>();
    MutableLiveData<Response<MovieListResponse>> movieListResponse = new MutableLiveData<>();

    public LiveData<ArrayList<Movie>> getMovies() {
        return this.movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies.postValue(movies);
    }
    public LiveData<Response<MovieListResponse>> getMovieListResponse() {
        return movieListResponse;
    }

    public void setMovieListResponse(Response<MovieListResponse> movieListResponse) {
        this.movieListResponse.postValue(movieListResponse);
    }

    public void setMovieCataloguePresenter(MovieCataloguePresenter movieCataloguePresenter) {
        if(this.movieCataloguePresenter == null)
            this.movieCataloguePresenter = movieCataloguePresenter;
    }
    public void setRecyclerPresenter(MovieRecyclerPresenter recyclerPresenter){
        if(this.recyclerPresenter==null)
            this.recyclerPresenter = recyclerPresenter;
    }

    public MovieCataloguePresenter getMovieCataloguePresenter() {
        return this.movieCataloguePresenter;
    }
    public MovieRecyclerPresenter getRecyclerPresenter(){
        return this.recyclerPresenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        recyclerPresenter = null;
        movieCataloguePresenter = null;
    }
}
