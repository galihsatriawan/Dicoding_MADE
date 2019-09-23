package id.shobrun.consumer.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import id.shobrun.consumer.R;
import id.shobrun.consumer.models.data.Movie;
import id.shobrun.consumer.repositories.ConsumerMovieRepository;
import id.shobrun.consumer.repositories.IConsumerMovieDataSource;

import id.shobrun.consumer.utils.Constants;
import id.shobrun.consumer.views.iview.IConsumerWishlistMovieView;

public class MovieWishListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> movies = new MutableLiveData<>();
    private ConsumerMovieRepository repository;
    private IConsumerWishlistMovieView mView;
    Context context;
    public void setAppView(Context context, IConsumerWishlistMovieView view){
        this.context = context;
        mView = view;
        this.repository = ConsumerMovieRepository.getInstance(context);
    }


    private void setMovies(ArrayList<Movie> movies) {
        this.movies.setValue(movies);
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return movies;
    }
    public void loadWishListMovie(){
        repository.getMovieByTags(Constants.TAGS_WISHLIST, new IConsumerMovieDataSource.DBSource.LoadDataCallback() {
            @Override
            public void onPreLoad() {
                mView.showProgress();
            }

            @Override
            public <T> void onLoadSuccess(T res) {
                ArrayList<Movie> movies = (ArrayList<Movie>) res;
                mView.hideProgress();
                MovieWishListViewModel.this.setMovies(movies);
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
