package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.ConsumerMovieRepository;
import id.shobrun.moviecatalogue.repositories.IConsumerMovieDataSource;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.iview.IDetailMovieWishListView;

public class DetailMovieWishListViewModel extends ViewModel {
    private final String TAG = getClass().getSimpleName();

    private MutableLiveData<Movie> movie = new MutableLiveData<>();
    private ConsumerMovieRepository repository;
    private Context context;
    private IDetailMovieWishListView view;

    public void setAppView(Context context, IDetailMovieWishListView view) {
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
                Log.d(TAG, "onLoadSuccess: " + res_movie.getTitle());
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
                    if(res_movie.getTags().contains(Constants.TAGS_WISHLIST)){
                        view.setIconWishList(R.drawable.ic_bookmark_white_24dp);
                    }
                }
            }
        });
    }

    public void updateMovieAfterAction(final Movie movie) {

        if (movie.getTags() == null) {
            movie.setTags(Constants.TAGS_WISHLIST);
            // insert
            repository.insertMovieLocal(movie, new IConsumerMovieDataSource.DBSource.UpdateDataCallback() {
                @Override
                public void onPreExecute() {

                }

                @Override
                public <T> void onPostExecute(T res) {
                    view.setIconWishList(R.drawable.ic_bookmark_white_24dp);
                    view.showMessageToast(context.getString(R.string.success_wishlist));

                }
            });
        } else {
            final String before = (movie.getTags() == null) ? "" : movie.getTags();
            Log.e(getClass().getSimpleName(), "updateMovieAfterAction: " + before + movie.getId());
            if (before.contains(Constants.TAGS_WISHLIST)) {
                movie.setTags(movie.getTags().replace(Constants.TAGS_WISHLIST, ""));
                // remove tags
                repository.updateMovieLocal(movie, new IConsumerMovieDataSource.DBSource.UpdateDataCallback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public <T> void onPostExecute(T res) {
                        view.setIconWishList(R.drawable.ic_bookmark_border_white_48dp);
                        view.showMessageToast(context.getString(R.string.remove_wishlist));
                    }
                });
            } else {
                movie.setTags(movie.getTags() + Constants.TAGS_WISHLIST);
                // update
                repository.updateMovieLocal(movie, new IConsumerMovieDataSource.DBSource.UpdateDataCallback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public <T> void onPostExecute(T res) {
                        view.setIconWishList(R.drawable.ic_bookmark_white_24dp);
                        view.showMessageToast(context.getString(R.string.success_wishlist));

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
