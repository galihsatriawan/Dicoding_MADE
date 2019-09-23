package id.shobrun.consumer.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import id.shobrun.consumer.R;
import id.shobrun.consumer.models.data.Movie;
import id.shobrun.consumer.repositories.ConsumerMovieRepository;
import id.shobrun.consumer.repositories.IConsumerMovieDataSource;
import id.shobrun.consumer.utils.Constants;
import id.shobrun.consumer.views.iview.IConsumerDetailMovieView;

public class ConsumerDetailMovieViewModel extends ViewModel {
    private final String TAG = getClass().getSimpleName();

    private MutableLiveData<Movie> movie = new MutableLiveData<>();
    private ConsumerMovieRepository repository;
    private Context context;
    private IConsumerDetailMovieView view;

    public void setAppView(Context context, IConsumerDetailMovieView view) {
        this.context = context;
        this.view = view;
        this.repository = new ConsumerMovieRepository(context);
    }

    public void setMovie(Movie movie) {
        this.movie.setValue(movie);
    }

    public void getMovieById(final int id) {
        repository.getMovieByIdLocal(id, new IConsumerMovieDataSource.DBSource.LoadDataCallback() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public <T> void onLoadSuccess(T res) {
                Movie res_movie = (Movie) res;
                setMovie(res_movie);
                view.hideProgress();
                view.showDetailMovie(res_movie);
            }
        });
    }

    public void checkMovieById(final int id) {

        repository.getMovieByIdLocal(id, new IConsumerMovieDataSource.DBSource.LoadDataCallback() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public <T> void onLoadSuccess(T res) {
                Movie res_movie = (Movie) res;
                if (res_movie.getId() != -1) {
                    movie.postValue(res_movie);
                    if(res_movie.getTags().contains(Constants.TAGS_FAVORITE)){
                        view.setIconFavorite(R.drawable.ic_favorite_black_24dp);
                    }
                }
            }
        });
    }

    public void updateMovieAfterAction(final Movie movie) {

        if (movie.getTags() == null) {
            movie.setTags(Constants.TAGS_FAVORITE);
            // insert
            repository.insertMovieLocal(movie, new IConsumerMovieDataSource.DBSource.UpdateDataCallback() {
                @Override
                public void onPreExecute() {

                }

                @Override
                public <T> void onPostExecute(T res) {
                    view.setIconFavorite(R.drawable.ic_favorite_black_24dp);
                    view.showMessageToast(context.getString(R.string.success_favorite));

                }
            });
        } else {
            final String before = (movie.getTags() == null) ? "" : movie.getTags();
            Log.e(getClass().getSimpleName(), "updateMovieAfterAction: " + before + movie.getId());
            if (before.contains(Constants.TAGS_FAVORITE)) {
                movie.setTags(movie.getTags().replace(Constants.TAGS_FAVORITE, ""));
                // remove tags
                repository.updateMovieLocal(movie, new IConsumerMovieDataSource.DBSource.UpdateDataCallback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public <T> void onPostExecute(T res) {
                        view.setIconFavorite(R.drawable.ic_favorite_border_black_24dp);
                        view.showMessageToast(context.getString(R.string.remove_favorite));
                    }
                });
            } else {
                movie.setTags(movie.getTags() + Constants.TAGS_FAVORITE);
                // update
                repository.updateMovieLocal(movie, new IConsumerMovieDataSource.DBSource.UpdateDataCallback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public <T> void onPostExecute(T res) {
                        view.setIconFavorite(R.drawable.ic_favorite_black_24dp);
                        view.showMessageToast(context.getString(R.string.success_favorite));

                    }
                });
            }

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
