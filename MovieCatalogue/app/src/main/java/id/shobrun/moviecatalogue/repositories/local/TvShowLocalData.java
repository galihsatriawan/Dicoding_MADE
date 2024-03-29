package id.shobrun.moviecatalogue.repositories.local;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.database.MovieCatalogueDatabase;
import id.shobrun.moviecatalogue.database.dao.TvShowDao;
import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.repositories.ITvShowDataSource;
import id.shobrun.moviecatalogue.utils.Constants;

public class TvShowLocalData implements ITvShowDataSource.DBSource {
    private TvShowDao tvShowDao;

    public TvShowLocalData(Context context){
        Context context1 = context;
        MovieCatalogueDatabase db = MovieCatalogueDatabase.getDatabase(context);
        tvShowDao = db.tvShowDao();
    }
    private static class QueryAsyncTask extends AsyncTask<String , Void, List<TvShow>> {
        private WeakReference<LoadDataCallback> weakCallback;
        private WeakReference<TvShowDao> tvShowDao;
        QueryAsyncTask(TvShowDao dao, ITvShowDataSource.DBSource.LoadDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.tvShowDao = new WeakReference<>(dao);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ITvShowDataSource.DBSource.LoadDataCallback listener =weakCallback.get();
            if(listener!=null){
                listener.onPreLoad();
            }
        }

        @Override
        protected List<TvShow> doInBackground(String... params) {
            List<TvShow> res = this.tvShowDao.get().getAllTvShowByTags(params[0]);
            try {
                Thread.sleep(Constants.DELAY_BACKGROUND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(List<TvShow> listLiveData) {
            super.onPostExecute(listLiveData);
            ITvShowDataSource.DBSource.LoadDataCallback listener =weakCallback.get();
            if(listener!=null){
                if(listLiveData == null){
                    weakCallback.get().onLoadSuccess(new ArrayList<>());
                }else{
                    weakCallback.get().onLoadSuccess(listLiveData);
                }
            }
        }

    }
    private static class QueryByIdAsyncTask extends AsyncTask<Integer, Void, TvShow> {
        private WeakReference<ITvShowDataSource.DBSource.LoadDataCallback> weakCallback;
        private WeakReference<TvShowDao> tvShowDao;
        QueryByIdAsyncTask(TvShowDao dao, ITvShowDataSource.DBSource.LoadDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.tvShowDao = new WeakReference<>(dao);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ITvShowDataSource.DBSource.LoadDataCallback listener =weakCallback.get();
            if(listener!=null){
                listener.onPreLoad();
            }
        }

        @Override
        protected TvShow doInBackground(Integer... params) {
            TvShow res = this.tvShowDao.get().getTvShowById(params[0]);
            try {
                Thread.sleep(Constants.DELAY_BACKGROUND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(TvShow liveData) {
            super.onPostExecute(liveData);
            ITvShowDataSource.DBSource.LoadDataCallback listener =weakCallback.get();
            if(listener!=null){
                if(liveData == null ) {
                    TvShow movie = new TvShow(-1);
                    listener.onLoadSuccess(movie);
                }else{
                    listener.onLoadSuccess(liveData);
                }
            }
        }

    }
    private static class UpdateAsyncTask extends  AsyncTask<TvShow, Void, String>{
        private WeakReference<ITvShowDataSource.DBSource.UpdateDataCallback> weakCallback;
        private WeakReference<TvShowDao> tvShowDao;
        UpdateAsyncTask(TvShowDao tvShowDao, ITvShowDataSource.DBSource.UpdateDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.tvShowDao = new WeakReference<>(tvShowDao);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ITvShowDataSource.DBSource.UpdateDataCallback listener = weakCallback.get();
            if(listener!=null){
                listener.onPreExecute();
            }
        }

        @Override
        protected String doInBackground(TvShow... movies) {
            tvShowDao.get().updateTvShow(movies[0]);
            try {
                Thread.sleep(Constants.DELAY_BACKGROUND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return movies[0].getTags();
        }

        @Override
        protected void onPostExecute(String res) {
            super.onPostExecute(res);
            ITvShowDataSource.DBSource.UpdateDataCallback listener = weakCallback.get();
            if(listener!=null){
                listener.onPostExecute(res);
            }
        }
    }
    private static class InsertAsyncTask extends AsyncTask<TvShow,Void, Integer>{
        WeakReference<ITvShowDataSource.DBSource.UpdateDataCallback> callback;
        WeakReference<TvShowDao> dao;
        InsertAsyncTask(TvShowDao dao, ITvShowDataSource.DBSource.UpdateDataCallback callback){
            this.dao = new WeakReference<>(dao);
            this.callback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ITvShowDataSource.DBSource.UpdateDataCallback listener = callback.get();
            if(listener!=null){
                listener.onPreExecute();
            }

        }

        @Override
        protected Integer doInBackground(final TvShow... movies) {
            this.dao.get().insertTvShow(movies[0]);
            try {
                Thread.sleep(Constants.DELAY_BACKGROUND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return movies[0].getId();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            ITvShowDataSource.DBSource.UpdateDataCallback listener = callback.get();
            if(listener!=null){
                listener.onPostExecute(integer);
            }

        }
    }
    private static class DeleteAsyncTask extends AsyncTask<TvShow, Void,Void>{
        WeakReference<ITvShowDataSource.DBSource.UpdateDataCallback> callback;
        WeakReference<TvShowDao> dao;
        DeleteAsyncTask(TvShowDao dao, ITvShowDataSource.DBSource.UpdateDataCallback callback){
            this.callback = new WeakReference<>(callback);
            this.dao = new WeakReference<>(dao);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ITvShowDataSource.DBSource.UpdateDataCallback listener = callback.get();
            if(listener!=null){
                listener.onPreExecute();
            }
        }

        @Override
        protected Void doInBackground(TvShow... movies) {
            this.dao.get().deleteTvShow(movies[0]);
            try {
                Thread.sleep(Constants.DELAY_BACKGROUND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void Voids) {
            super.onPostExecute(Voids);
            ITvShowDataSource.DBSource.UpdateDataCallback listener = callback.get();
            if(listener!=null){
                listener.onPostExecute(null);
            }
        }
    }
    @Override
    public void insertTvShowLocal(TvShow tvShow, ITvShowDataSource.DBSource.UpdateDataCallback callback){
        InsertAsyncTask asyncTask ;
        synchronized (TvShowLocalData.class){
            asyncTask = new InsertAsyncTask(tvShowDao,callback);
        }
        asyncTask.execute(tvShow);
    }
    @Override
    public void getFavoriteTvShowLocal(String tags, ITvShowDataSource.DBSource.LoadDataCallback callback){
        QueryAsyncTask asyncTask;
        synchronized (TvShowLocalData.class){
            asyncTask = new QueryAsyncTask(tvShowDao,callback);
        }
        asyncTask.execute(tags);
    }

    @Override
    public void getTvShowByIdLocal(int id, LoadDataCallback callback) {
        QueryByIdAsyncTask asyncTask;
        synchronized (TvShowLocalData.class){
            asyncTask = new QueryByIdAsyncTask(tvShowDao,callback);
        }
        asyncTask.execute(id);
    }

    @Override
    public void updateTvShowLocal(TvShow tvShow, UpdateDataCallback callback) {
        UpdateAsyncTask asyncTask ;
        synchronized (TvShowLocalData.class){
            asyncTask = new UpdateAsyncTask(tvShowDao,callback);
        }
        asyncTask.execute(tvShow);
    }

    @Override
    public void deleteTvShowLocal(TvShow tvShow, ITvShowDataSource.DBSource.UpdateDataCallback callback){
        DeleteAsyncTask asyncTask ;
        synchronized (TvShowLocalData.class){
            asyncTask = new DeleteAsyncTask(tvShowDao,callback);
        }
        asyncTask.execute(tvShow);
    }
    
}
