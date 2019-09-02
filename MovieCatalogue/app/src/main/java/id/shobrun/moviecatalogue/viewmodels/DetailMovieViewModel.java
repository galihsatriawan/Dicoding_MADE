package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.iview.IDetailMovieView;

public class DetailMovieViewModel extends ViewModel {
    private MutableLiveData<Movie> movie = new MutableLiveData<>();
    private MovieRepository repository;
    private Context context;
    private IDetailMovieView view;
    public void setAppView(Context context, IDetailMovieView view){
        this.context = context;
        this.view = view;
        this.repository = new MovieRepository(context);
    }
    public void setMovie(Movie movie) {
        this.movie.setValue(movie);
    }
    public void checkMovieById(final int id){
        repository.getMovieByIdLocal(id, new IMoviesDataSource.DBSource.LoadDataCallback() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public <T> void onLoadSuccess(T res) {
                Movie res_movie = (Movie) res;
                if(res_movie.getId() != -1){
                    movie.postValue(res_movie);
                    view.setIconFavorite(R.drawable.ic_favorite_black_24dp);
                }
            }
        });
    }
    public void updateMovieAfterAction(final Movie movie){

        final String before =(movie.getTags()==null)?"":movie.getTags();
        Log.e(getClass().getSimpleName(), "updateMovieAfterAction: "+before+movie.getId());
        if(before.contains(Constants.TAGS_FAVORITE)){
            movie.setTags("");
            // remove
            repository.deleteMovieLocal(movie, new IMoviesDataSource.DBSource.UpdateDataCallback() {
                @Override
                public void onPreExecute() {

                }

                @Override
                public <T> void onPostExecute(T res) {
                    view.setIconFavorite(R.drawable.ic_favorite_border_black_24dp);
                    view.showMessage(context.getString(R.string.remove_favorite));
                }
            });
        }else{
            movie.setTags(Constants.TAGS_FAVORITE);
            // insert
            repository.insertMovieLocal(movie, new IMoviesDataSource.DBSource.UpdateDataCallback() {
                @Override
                public void onPreExecute() {

                }

                @Override
                public <T> void onPostExecute(T res) {
                    view.setIconFavorite(R.drawable.ic_favorite_black_24dp);
                    view.showMessage(context.getString(R.string.success_favorite));
                }
            });
        }
        this.setMovie(movie);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }
}
