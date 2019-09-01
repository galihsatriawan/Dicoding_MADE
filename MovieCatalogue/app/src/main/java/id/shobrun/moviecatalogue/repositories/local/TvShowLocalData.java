package id.shobrun.moviecatalogue.repositories.local;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.database.MovieCatalogueDatabase;
import id.shobrun.moviecatalogue.database.dao.TvShowDao;
import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.repositories.ITvShowDataSource;

public class TvShowLocalData implements ITvShowDataSource.DBSource {
    private TvShowDao tvShowDao;
    private Context context;
    private MovieCatalogueDatabase db;
    public TvShowLocalData(Context context){
        this.context =context;
        db = MovieCatalogueDatabase.getDatabase(context);
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
            weakCallback.get().onPreLoad();
        }

        @Override
        protected List<TvShow> doInBackground(String... params) {
            return this.tvShowDao.get().getAllTvShowByTags(params[0]);
        }

        @Override
        protected void onPostExecute(List<TvShow> listLiveData) {
            super.onPostExecute(listLiveData);
            if(listLiveData == null){
                Log.e(getClass().getSimpleName(), "onPostExecute: null" );
                weakCallback.get().onLoadSuccess(new ArrayList<>());
            }else{
                Log.e(getClass().getSimpleName(), "onPostExecute: "+listLiveData.toString() );
                weakCallback.get().onLoadSuccess(listLiveData);
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
            weakCallback.get().onPreLoad();
        }

        @Override
        protected TvShow doInBackground(Integer... params) {
            return this.tvShowDao.get().getTvShowById(params[0]);
        }

        @Override
        protected void onPostExecute(TvShow liveData) {
            super.onPostExecute(liveData);
            Log.e(getClass().getSimpleName(), "onPostExecute: "+weakCallback.get().toString() );
            if(liveData == null ) {
                TvShow movie = new TvShow(-1);
                weakCallback.get().onLoadSuccess(movie);
            }else{
                weakCallback.get().onLoadSuccess(liveData);
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
            weakCallback.get().onPreExecute();
        }

        @Override
        protected String doInBackground(TvShow... movies) {
            tvShowDao.get().updateTvShow(movies[0]);
            return movies[0].getTags();
        }

        @Override
        protected void onPostExecute(String res) {
            super.onPostExecute(res);
            weakCallback.get().onPostExecute(res);
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
            this.callback.get().onPreExecute();
        }

        @Override
        protected Integer doInBackground(final TvShow... movies) {
            this.dao.get().insertTvShow(movies[0]);
            return movies[0].getId();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            this.callback.get().onPostExecute(integer);
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
            this.callback.get().onPreExecute();
        }

        @Override
        protected Void doInBackground(TvShow... movies) {
            this.dao.get().deleteTvShow(movies[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void Voids) {
            super.onPostExecute(Voids);
            this.callback.get().onPostExecute(null);
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
