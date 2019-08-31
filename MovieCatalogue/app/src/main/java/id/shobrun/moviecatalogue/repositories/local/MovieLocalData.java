package id.shobrun.moviecatalogue.repositories.local;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import id.shobrun.moviecatalogue.database.MovieCatalogueDatabase;
import id.shobrun.moviecatalogue.database.dao.MovieDao;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;

public class MovieLocalData implements IMoviesDataSource.DBSource {
    private MovieDao movieDao;
    private MovieCatalogueDatabase db;

    public MovieLocalData(Application application){
        db = MovieCatalogueDatabase.getDatabase(application);
        movieDao = db.movieDao();
    }
    private static class QueryAsyncTask extends AsyncTask<String , Void, LiveData<List<Movie>>> {
        private WeakReference<IMoviesDataSource.DBSource.LoadDataCallback> weakCallback;
        private WeakReference<MovieDao> movieDao;
        QueryAsyncTask(MovieDao dao, IMoviesDataSource.DBSource.LoadDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.movieDao = new WeakReference<>(dao);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().onPreLoad();
        }

        @Override
        protected LiveData<List<Movie>> doInBackground(String... params) {
            return this.movieDao.get().getAllMovieByTags(params[0]);
        }

        @Override
        protected void onPostExecute(LiveData<List<Movie>> listLiveData) {
            super.onPostExecute(listLiveData);
            weakCallback.get().onLoadSuccess(listLiveData);
        }

    }
    private static class InsertAsyncTask extends AsyncTask<Movie,Void, Integer>{
        WeakReference<IMoviesDataSource.DBSource.UpdateDataCallback> callback;
        WeakReference<MovieDao> dao;
        InsertAsyncTask(MovieDao dao, IMoviesDataSource.DBSource.UpdateDataCallback callback){
            this.dao = new WeakReference<>(dao);
            this.callback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.callback.get().onPreExecute();
        }

        @Override
        protected Integer doInBackground(final Movie... movies) {
            this.dao.get().insertMovie(movies[0]);
            return movies[0].getId();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            this.callback.get().onPostExecute(integer);
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Movie, Void,Void>{
        WeakReference<IMoviesDataSource.DBSource.UpdateDataCallback> callback;
        WeakReference<MovieDao> dao;
        DeleteAsyncTask(MovieDao dao, IMoviesDataSource.DBSource.UpdateDataCallback callback){
            this.callback = new WeakReference<>(callback);
            this.dao = new WeakReference<>(dao);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.callback.get().onPreExecute();
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            this.dao.get().deleteMovie(movies[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void Voids) {
            super.onPostExecute(Voids);
            this.callback.get().onPostExecute(null);
        }
    }
    @Override
    public void insertMovieLocal(Movie movie, IMoviesDataSource.DBSource.UpdateDataCallback callback){
        InsertAsyncTask asyncTask = new InsertAsyncTask(movieDao,callback);
        asyncTask.execute(movie);

    }
    @Override
    public void getLikeMoviesLocal(String tags, IMoviesDataSource.DBSource.LoadDataCallback callback){
        QueryAsyncTask asyncTask = new QueryAsyncTask(movieDao,callback);
        asyncTask.execute(tags);
    }
    @Override
    public void deleteMovieLocal(Movie movie, IMoviesDataSource.DBSource.UpdateDataCallback callback){
        DeleteAsyncTask asyncTask = new DeleteAsyncTask(movieDao,callback);
        asyncTask.execute(movie);
    }
}
