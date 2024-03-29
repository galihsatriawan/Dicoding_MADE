package id.shobrun.moviecatalogue.repositories.remote;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import id.shobrun.moviecatalogue.api.ApiClient;
import id.shobrun.moviecatalogue.api.ApiInterface;
import id.shobrun.moviecatalogue.repositories.ITvShowDataSource;
import id.shobrun.moviecatalogue.BuildConfig;
import id.shobrun.moviecatalogue.api.response.TvShowListResponse;
import id.shobrun.moviecatalogue.models.data.TvShow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowRemoteData implements ITvShowDataSource.ApiSource {
    private ApiInterface apiService;

    private ArrayList<TvShow> tvShows;
    private ArrayList<TvShow> tvShowsPopular;
    private ArrayList<TvShow> tvShowsTrending;
    public TvShowRemoteData(Context context){
        Context context1 = context;
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }
    @Override
    public ArrayList<TvShow> getAllTvShowPopular(final ITvShowDataSource.ApiSource.OnFinishedListener onFinishedListener) {
        try {
            HashMap<String,String> options = new HashMap<>();
            options.put("api_key", BuildConfig.ApiKey);
            options.put("language","en-US");

            Call<TvShowListResponse> response = apiService.getTvShow(options);
            response.enqueue(new Callback<TvShowListResponse>() {
                @Override
                public void onResponse(Call<TvShowListResponse> call, Response<TvShowListResponse> response) {
                    if(response.isSuccessful()){
                        onFinishedListener.onFinished(response);
                        tvShowsPopular = response.body().getResults();
                    }else {
                        onFinishedListener.onError(response);
                    }
                }

                @Override
                public void onFailure(Call<TvShowListResponse> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }catch (Throwable t){
            onFinishedListener.onFailure(t);
            t.printStackTrace();
        }
        return tvShowsPopular;
    }

    @Override
    public ArrayList<TvShow> getAllTvShowTrending(final OnFinishedListener onFinishedListener) {
        try{
            HashMap<String,String> options = new HashMap<>();
            options.put("api_key", BuildConfig.ApiKey);
            options.put("language","en-US");

            Call<TvShowListResponse> response = apiService.getTvShow(options);
            response.enqueue(new Callback<TvShowListResponse>() {
                @Override
                public void onResponse(Call<TvShowListResponse> call, Response<TvShowListResponse> response) {
                    if (response.isSuccessful()){
                        onFinishedListener.onFinished(response);
                        tvShowsTrending= response.body().getResults();
                    }else {
                        onFinishedListener.onError(response);
                    }
                }

                @Override
                public void onFailure(Call<TvShowListResponse> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return tvShowsTrending;
    }

    @Override
    public ArrayList<TvShow> getSearchTvShow(String str, final OnFinishedListener onFinishedListener) {
        try {
            HashMap<String, String > options = new HashMap<>();
            options.put("api_key",BuildConfig.ApiKey);
            options.put("language","en-US");
            options.put("query",str);

            Call<TvShowListResponse> response = apiService.getSearchTv(options);
            response.enqueue(new Callback<TvShowListResponse>() {
                @Override
                public void onResponse(Call<TvShowListResponse> call, Response<TvShowListResponse> response) {
                    if(response.isSuccessful()){
                        onFinishedListener.onFinished(response);
                    }else{
                        onFinishedListener.onError(response);
                    }
                }

                @Override
                public void onFailure(Call<TvShowListResponse> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }catch (Throwable t){
            onFinishedListener.onFailure(t);
        }
        return tvShows;
    }


    @Override
    public int getTvShow(int id) {
        int position = -1;
        int index = 0;
        for (TvShow item: tvShows) {
            if(item.getId()==id) {
                position = index;
                break;
            }
            index++;
        }
        return position;
    }
    @Override
    public int getTvShowCount() {
        return tvShows.size();
    }
}
