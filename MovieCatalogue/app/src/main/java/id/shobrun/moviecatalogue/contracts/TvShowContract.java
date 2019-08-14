package id.shobrun.moviecatalogue.contracts;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.component.data.TvShow;
import id.shobrun.moviecatalogue.component.response.TvShowListResponse;
import id.shobrun.moviecatalogue.models.MovieModel;
import retrofit2.Response;

public interface TvShowContract {
    interface Model{
        interface OnFinishedListener{
            void onSuccess(Response<TvShowListResponse> response);
            void onError(Response<TvShowListResponse> response);
            void onFailure(Throwable t);
        }
        ArrayList<TvShow> getAllTvShowPopular(OnFinishedListener onFinishedListener);
        ArrayList<TvShow> getAllTvShowTrending(OnFinishedListener onFinishedListener);
        int getTvShow(int id);
        int getTvShowCount();
    }
    interface View {
        void showProgress();
        void hideProgress();
        void initUI();
        void showListTvShowPopular(ArrayList<TvShow> tvShows);
        void showListTvShowTrending(ArrayList<TvShow> tvShows);
        void showMessage(String message);
    }
    interface Presenter{
        void loadTvShowPopular();
        void loadTvShowTrending();
    }
}
