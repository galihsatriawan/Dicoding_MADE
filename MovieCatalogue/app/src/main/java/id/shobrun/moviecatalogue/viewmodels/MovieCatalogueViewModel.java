package id.shobrun.moviecatalogue.viewmodels;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.ArrayList;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.MovieRepository;


public class MovieCatalogueViewModel extends ViewModel {
    private  MovieRepository repository ;
    MovieCatalogueViewModel(Application application){
        repository = new MovieRepository(application);
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
