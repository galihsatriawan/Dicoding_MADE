package id.shobrun.moviecatalogue.repositories.local;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.database.MovieCatalogueDatabase;
import id.shobrun.moviecatalogue.database.dao.MovieDao;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;

public class MovieLocalData implements IMoviesDataSource.DBSource {
    private MovieDao movieDao;

    public MovieLocalData(Context context){
        Context context1 = context;
        MovieCatalogueDatabase db = MovieCatalogueDatabase.getDatabase(context);
        movieDao = db.movieDao();
    }
    private static class QueryAsyncTask extends AsyncTask<String , Void, List<Movie>> {
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
        protected List<Movie> doInBackground(String... params) {
            return this.movieDao.get().getAllMovieByTags(params[0]);
        }

        @Override
        protected void onPostExecute(List<Movie> listLiveData) {
            super.onPostExecute(listLiveData);
            if(listLiveData == null){

                weakCallback.get().onLoadSuccess(new ArrayList<>());
            }else{
                weakCallback.get().onLoadSuccess(listLiveData);
            }
        }

    }
    private static class QueryByIdAsyncTask extends AsyncTask<Integer, Void, Movie> {
        private WeakReference<IMoviesDataSource.DBSource.LoadDataCallback> weakCallback;
        private WeakReference<MovieDao> movieDao;
        QueryByIdAsyncTask(MovieDao dao, IMoviesDataSource.DBSource.LoadDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.movieDao = new WeakReference<>(dao);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().onPreLoad();
        }

        @Override
        protected Movie doInBackground(Integer... params) {
            return this.movieDao.get().getMovieById(params[0]);
        }

        @Override
        protected void onPostExecute(Movie liveData) {
            super.onPostExecute(liveData);
            if(liveData == null ) {
                Movie movie = new Movie(-1);
                weakCallback.get().onLoadSuccess(movie);
            }else{
                weakCallback.get().onLoadSuccess(liveData);
            }


        }

    }
    private static class UpdateAsyncTask extends  AsyncTask<Movie, Void, String>{
        private WeakReference<IMoviesDataSource.DBSource.UpdateDataCallback> weakCallback;
        private WeakReference<MovieDao> movieDao;
        UpdateAsyncTask(MovieDao movieDao, IMoviesDataSource.DBSource.UpdateDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.movieDao = new WeakReference<>(movieDao);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().onPreExecute();
        }

        @Override
        protected String doInBackground(Movie... movies) {
            movieDao.get().updateMovie(movies[0]);
            return movies[0].getTags();
        }

        @Override
        protected void onPostExecute(String res) {
            super.onPostExecute(res);
            weakCallback.get().onPostExecute(res);
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
        InsertAsyncTask asyncTask ;
        synchronized (MovieLocalData.class){
            asyncTask = new InsertAsyncTask(movieDao,callback);
            asyncTask.execute(movie);
        }
    }
    @Override
    public void getLikeMoviesLocal(String tags, IMoviesDataSource.DBSource.LoadDataCallback callback){
        QueryAsyncTask asyncTask;
        synchronized (MovieLocalData.class){
            asyncTask = new QueryAsyncTask(movieDao,callback);
            asyncTask.execute(tags);
        }

    }

    @Override
    public ArrayList<Movie> getLikeMoviesLocalSync(String tags) {

        return (ArrayList)movieDao.getAllMovieByTags(tags);
    }

    @Override
    public void getMovieByIdLocal(int id, LoadDataCallback callback) {
        QueryByIdAsyncTask asyncTask;
        synchronized (MovieLocalData.class){
            asyncTask = new QueryByIdAsyncTask(movieDao,callback);
            asyncTask.execute(id);
        }

    }

    @Override
    public void updateMovieLocal(Movie movie, UpdateDataCallback callback) {
        UpdateAsyncTask asyncTask ;
        synchronized (MovieLocalData.class){
            asyncTask = new UpdateAsyncTask(movieDao,callback);
        }
        asyncTask.execute(movie);
    }

    @Override
    public void deleteMovieLocal(Movie movie, IMoviesDataSource.DBSource.UpdateDataCallback callback){
        DeleteAsyncTask asyncTask ;
        synchronized (MovieLocalData.class){
            asyncTask = new DeleteAsyncTask(movieDao,callback);
        }
        asyncTask.execute(movie);
    }
}
