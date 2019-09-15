package id.shobrun.moviecatalogue.viewmodels;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.ConsumerMovieRepository;
import id.shobrun.moviecatalogue.repositories.IConsumerMovieDataSource;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.iview.IMovieCatalogueView;
import retrofit2.Response;


public class MovieCatalogueViewModel extends ViewModel {
    private MovieRepository mRepository;
    private ConsumerMovieRepository mConsumerRepository;
    private Context context;
    private IMovieCatalogueView mView;
    /**
     * Keep observe data
     */
    private MutableLiveData<ArrayList<Movie>> movies;

    public void setAppView(Context context, IMovieCatalogueView view) {
        this.context = context;
        mRepository = MovieRepository.getInstance(context);
        mConsumerRepository = ConsumerMovieRepository.getInstance(context);
        mView = view;
    }

    public void loadAllMovie() {
        mView.showProgress();
        mRepository.getMoviesData(new IMoviesDataSource.ApiSource.OnFinishedListener() {
            @Override
            public void onFinished(Response<MovieListResponse> response) {
                mView.hideProgress();
                ArrayList<Movie> result = response.body().getResults();
                setMovies(result);
                if (result.size() == 0) {
                    mView.showMessage(context.getResources().getString(R.string.empty_movie));
                }
            }

            @Override
            public void onRefresh(ArrayList<Movie> movies) {
                mView.hideProgress();
                setMovies(movies);
                if (movies.size() == 0) {
                    mView.showMessage(context.getResources().getString(R.string.empty_movie));
                }

            }

            @Override
            public void onError(Response<MovieListResponse> response) {
                mView.hideProgress();
                mView.showMessage(context.getString(R.string.communication_error));
            }

            @Override
            public void onFailure(Throwable t) {
                mView.hideProgress();
                mView.showMessage(context.getString(R.string.communication_error));
            }
        });
    }

    public void updateMovieAfterAction(final Movie movie) {
        /**
         * Check has been in Database or not , because use Tags identifier for Favorite & WishList Movie
         */
        mConsumerRepository.getMovieByIdLocal(movie.getId(), new IConsumerMovieDataSource.DBSource.LoadDataCallback() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public <T> void onLoadSuccess(T res) {
                Movie res_movie = (Movie) res;
                if (res_movie.getId() == -1) {
                    // Not Save

                    movie.setTags(Constants.TAGS_WISHLIST);
                    // insert
                    mConsumerRepository.insertMovieLocal(movie, new IConsumerMovieDataSource.DBSource.UpdateDataCallback() {
                        @Override
                        public void onPreExecute() {

                        }

                        @Override
                        public <T> void onPostExecute(T res) {

                            mView.showMessageToast(movie.getTitle() + " has added");

                        }
                    });
                } else {

                    final String before = (res_movie.getTags() == null) ? "" : res_movie.getTags();
                    Log.e(getClass().getSimpleName(), "updateMovieAfterAction: " + before + movie.getTags());
                    if (before.contains(Constants.TAGS_WISHLIST)) {
                        res_movie.setTags(res_movie.getTags().replace(Constants.TAGS_WISHLIST, ""));
                        // remove tag
                        mConsumerRepository.updateMovieLocal(movie, new IConsumerMovieDataSource.DBSource.UpdateDataCallback() {
                            @Override
                            public void onPreExecute() {

                            }

                            @Override
                            public <T> void onPostExecute(T res) {

                                mView.showMessageToast(movie.getTitle() + " has removed");
                            }
                        });
                    } else {
                        //update tag
                        res_movie.setTags(res_movie.getTags() + Constants.TAGS_WISHLIST);
                        mConsumerRepository.updateMovieLocal(res_movie, new IConsumerMovieDataSource.DBSource.UpdateDataCallback() {
                            @Override
                            public void onPreExecute() {

                            }

                            @Override
                            public <T> void onPostExecute(T res) {

                                mView.showMessageToast(movie.getTitle() + " has added");

                            }
                        });
                    }
                }
            }
        });

    }

    public void loadSearchMovie(final String str) {
        mView.showProgress();
        mRepository.getSearchMoviesData(str, new IMoviesDataSource.ApiSource.OnFinishedListener() {
            @Override
            public void onFinished(Response<MovieListResponse> response) {
                mView.hideProgress();
                ArrayList<Movie> result = response.body().getResults();
                setMovies(result);
                if (result.size() == 0) {
                    mView.showMessage(context.getResources().getString(R.string.empty_movie));
                }
            }

            @Override
            public void onRefresh(ArrayList<Movie> movies) {
                mView.hideProgress();
                setMovies(movies);
                if (movies.size() == 0) {
                    mView.showMessage(context.getResources().getString(R.string.empty_movie));
                }

            }

            @Override
            public void onError(Response<MovieListResponse> response) {
                mView.hideProgress();
                mView.showMessage(context.getString(R.string.communication_error));
            }

            @Override
            public void onFailure(Throwable t) {
                mView.hideProgress();
                mView.showMessage(context.getString(R.string.communication_error));
            }
        });
    }



    public LiveData<ArrayList<Movie>> getMovies() {
        if (movies == null) return movies = new MutableLiveData<>();
        return movies;
    }

    private void setMovies(ArrayList<Movie> movies) {
        if (this.movies == null) {
            this.movies = new MutableLiveData<>();
        }
        this.movies.postValue(movies);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
