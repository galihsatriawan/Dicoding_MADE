package id.shobrun.moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.repositories.ITvShowDataSource;
import id.shobrun.moviecatalogue.repositories.TvShowRepository;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.iview.IDetailTvShowView;

public class DetailTvShowViewModel extends ViewModel {
    private MutableLiveData<TvShow> tvShow = new MutableLiveData<>();
    private TvShowRepository repository;
    private Context context;
    private IDetailTvShowView view;
    public void setAppView(Context context, IDetailTvShowView view){
        this.context = context;
        this.view = view;
        this.repository = new TvShowRepository(context);
    }
    public void setTvShow(TvShow tvShow) {
        this.tvShow.postValue(tvShow);
    }
    public void checkTvShowById(final int id){
        repository.getTvShowByIdLocal(id, new ITvShowDataSource.DBSource.LoadDataCallback() {
            @Override
            public void onPreLoad() {

            }

            @Override
            public <T> void onLoadSuccess(T res) {
                TvShow res_tv= (TvShow) res;
                if(res_tv.getId() != -1){
                    tvShow.postValue(res_tv);
                    view.setIconFavorite(R.drawable.ic_favorite_black_24dp);
                }
            }
        });
    }
    public void updateTvShowAfterAction(final TvShow tvShow){

        final String before =(tvShow.getTags()==null)?"":tvShow.getTags();
        Log.e(getClass().getSimpleName(), "updateTvShowAfterAction: "+before+tvShow.getId());
        if(before.contains(Constants.TAGS_FAVORITE)){
            tvShow.setTags("");
            // remove
            repository.deleteTvShowLocal(tvShow, new ITvShowDataSource.DBSource.UpdateDataCallback() {
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
            tvShow.setTags(Constants.TAGS_FAVORITE);
            // insert
            repository.insertTvShowLocal(tvShow, new ITvShowDataSource.DBSource.UpdateDataCallback() {
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
        this.tvShow.postValue(tvShow);

    }

    public LiveData<TvShow> getTvShow() {
        return tvShow;
    }
}
