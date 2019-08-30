package id.shobrun.moviecatalogue.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.List;

import id.shobrun.moviecatalogue.database.MovieCatalogueDatabase;
import id.shobrun.moviecatalogue.database.dao.MovieDao;
import id.shobrun.moviecatalogue.models.data.Movie;

public class MovieRepository implements IMoviesDataSource {
    private MutableLiveData<List<Movie>> listMovies = new MutableLiveData<>();
    private MovieCatalogueDatabase db;
    private MovieDao movieDao;
    public MovieRepository(Application application){
        db = MovieCatalogueDatabase.getDatabase(application);
        movieDao = db.movieDao();
    }

    private static class QueryAsyncTask extends AsyncTask<String , Void, LiveData<List<Movie>>>{
        private WeakReference<LoadDataCallback> weakCallback;
        private WeakReference<MovieDao> movieDao;
        QueryAsyncTask(MovieDao dao, LoadDataCallback callback){
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
        WeakReference<UpdateDataCallback> callback;
        WeakReference<MovieDao> dao;
        InsertAsyncTask(MovieDao dao,UpdateDataCallback callback){
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
        WeakReference<UpdateDataCallback> callback;
        WeakReference<MovieDao> dao;
        DeleteAsyncTask(MovieDao dao, UpdateDataCallback callback){
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
    public void insertMovieLocal(Movie movie,UpdateDataCallback callback){
        InsertAsyncTask asyncTask = new InsertAsyncTask(movieDao,callback);
        asyncTask.execute(movie);

    }
    public void getLikeMoviesLocal(LoadDataCallback callback){
        QueryAsyncTask asyncTask = new QueryAsyncTask(movieDao,callback);
        asyncTask.execute();
    }
    public void deleteMovieLocal(Movie movie,UpdateDataCallback callback){
        DeleteAsyncTask asyncTask = new DeleteAsyncTask(movieDao,callback);
        asyncTask.execute(movie);
    }
    @Override
    public void getMoviesData(GetMoviesDataCallback callback) {

    }
}
