package id.shobrun.moviecatalogue.repositories;

import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.api.response.TvShowListResponse;
import id.shobrun.moviecatalogue.contracts.TvShowContract;
import id.shobrun.moviecatalogue.models.data.TvShow;
import retrofit2.Response;

public interface ITvShowDataSource {
    interface ApiSource{
        ArrayList<TvShow> getAllTvShowPopular(OnFinishedListener onFinishedListener);
        ArrayList<TvShow> getAllTvShowTrending(OnFinishedListener onFinishedListener);
        int getTvShow(int position);
        int getTvShowCount();
        interface OnFinishedListener{
            void onFinished(Response<TvShowListResponse> response);
            void onRefresh(ArrayList<TvShow> tvShow);
            void onError(Response<TvShowListResponse> response);
            void onFailure(Throwable t);
        }
    }
    interface DBSource {
        void updateTvShowLocal(TvShow tvShow, UpdateDataCallback callback);

        void deleteTvShowLocal(TvShow tvShow, UpdateDataCallback callback);

        void insertTvShowLocal(TvShow tvShow, UpdateDataCallback callback);

        void getFavoriteTvShowLocal(String tags, LoadDataCallback callback);

        void getTvShowByIdLocal(int id, LoadDataCallback callback);

        interface LoadDataCallback {
            void onPreLoad();

            <T> void onLoadSuccess(T res);
        }

        interface UpdateDataCallback {
            void onPreExecute();

            <T> void onPostExecute(T res);
        }
    }
}
