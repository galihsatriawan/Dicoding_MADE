package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.shobrun.moviecatalogue.models.data.Movie;

public class DetailMovieViewModel extends ViewModel {
    MutableLiveData<Movie> movie = new MutableLiveData<>();

    public void setMovie(Movie movie) {
        this.movie.postValue(movie);
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }
}
