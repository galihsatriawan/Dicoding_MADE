package id.shobrun.moviecatalogue.repositories.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.ConsumerMovieRepository;
import id.shobrun.moviecatalogue.repositories.IConsumerMovieDataSource;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.utils.DataPair;
import id.shobrun.moviecatalogue.utils.Helper;

public class ConsumerMovieLocalData implements IConsumerMovieDataSource.DBSource {
    private static String TAG = ConsumerMovieLocalData.class.getSimpleName();
    private Context context;
    private ContentResolver mContentResolver;
    public ConsumerMovieLocalData(Context context){
        this.context = context;
        mContentResolver = context.getContentResolver();
    }
    private static class QueryAsyncTask extends AsyncTask<DataPair, Void, List<Movie>> {
        private WeakReference<LoadDataCallback> weakCallback;
        private WeakReference<ContentResolver> weakResolver;
        QueryAsyncTask(ContentResolver resolver, IConsumerMovieDataSource.DBSource.LoadDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.weakResolver = new WeakReference<>(resolver);
        }


        @Override
        protected List<Movie> doInBackground(DataPair... dataPairs) {
            Cursor cursor;
            if(dataPairs[0].argument!=null){
                cursor = weakResolver.get().query(dataPairs[0].uri,null,dataPairs[0].selection,dataPairs[0].argument,null);
                Log.d(TAG, "doInBackground: "+dataPairs[0].argument[0]);
            }else{
                cursor = weakResolver.get().query(dataPairs[0].uri,null,null,null,null);
            }
            ArrayList<Movie> movies = Helper.mappingCursorToMovies(cursor);
            Log.d(TAG, "doInBackground size: "+movies.size());
            return movies;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().onPreLoad();
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
    private static class QueryByIdAsyncTask extends AsyncTask<Uri, Void, Movie> {
        private WeakReference<IConsumerMovieDataSource.DBSource.LoadDataCallback> weakCallback;
        private WeakReference<ContentResolver> weakResolver;
        QueryByIdAsyncTask(ContentResolver resolver, IConsumerMovieDataSource.DBSource.LoadDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.weakResolver = new WeakReference<>(resolver);
        }

        @Override
        protected Movie doInBackground(Uri... uris) {
            Log.d(TAG, "doInBackground: "+uris[0]);
            Cursor cursor = weakResolver.get().query(uris[0],null,null,null,null);
            Movie movie = new Movie(-1);
            ArrayList<Movie> temp = Helper.mappingCursorToMovies(cursor);
            if(temp.size()>0){
                 movie= temp.get(0);
            }
            return movie;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().onPreLoad();
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

    private static class UpdateAsyncTask extends  AsyncTask<DataPair, Void, Integer>{
        private WeakReference<IConsumerMovieDataSource.DBSource.UpdateDataCallback> weakCallback;
        private WeakReference<ContentResolver> weakResolver;
        UpdateAsyncTask(ContentResolver resolver, IConsumerMovieDataSource.DBSource.UpdateDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.weakResolver = new WeakReference<>(resolver);
        }


        @Override
        protected Integer doInBackground(DataPair... dataPairs) {
            Integer res = weakResolver.get().update(dataPairs[0].uri,dataPairs[0].values,null,null);
            return res;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            weakCallback.get().onPostExecute(integer);
        }
    }
    private static class InsertAsyncTask extends AsyncTask<DataPair,Void, Uri>{
        private WeakReference<IConsumerMovieDataSource.DBSource.UpdateDataCallback> weakCallback;
        private WeakReference<ContentResolver> weakResolver;
        InsertAsyncTask(ContentResolver resolver, IConsumerMovieDataSource.DBSource.UpdateDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.weakResolver = new WeakReference<>(resolver);
        }

        @Override
        protected Uri doInBackground(DataPair... dataPairs) {
            Uri res = weakResolver.get().insert(dataPairs[0].uri,dataPairs[0].values);
            Log.d(TAG, "insertMovieLocal background: "+dataPairs[0].values.getAsString(Movie.TAGS));
            Log.d(TAG, "doInBackground URI: "+res);
            return res;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.weakCallback.get().onPreExecute();
        }

        @Override
        protected void onPostExecute(Uri uri) {
            super.onPostExecute(uri);
            weakCallback.get().onPostExecute(uri);
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Uri, Void,Integer>{
        private WeakReference<IConsumerMovieDataSource.DBSource.UpdateDataCallback> weakCallback;
        private WeakReference<ContentResolver> weakResolver;
        DeleteAsyncTask(ContentResolver resolver, IConsumerMovieDataSource.DBSource.UpdateDataCallback callback){
            this.weakCallback = new WeakReference<>(callback);
            this.weakResolver = new WeakReference<>(resolver);
        }

        @Override
        protected Integer doInBackground(Uri... uris) {
            Integer res = weakResolver.get().delete(uris[0],null,null);
            return res;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.weakCallback.get().onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            weakCallback.get().onPostExecute(integer);
        }
    }
    @Override
    public void insertMovieLocal(Movie movie, IConsumerMovieDataSource.DBSource.UpdateDataCallback callback){
        InsertAsyncTask asyncTask ;
        DataPair dataPair = new DataPair(Movie.CONTENT_URI,Helper.mappingMoviesToContent(movie));
        Log.d(TAG, "insertMovieLocal: "+dataPair.values.getAsString(Movie.TAGS));
        synchronized (ConsumerMovieLocalData.class){
            asyncTask = new InsertAsyncTask(mContentResolver,callback);
            asyncTask.execute(dataPair);
        }
    }
    @Override
    public void getWishListMoviesLocal(String tags, IConsumerMovieDataSource.DBSource.LoadDataCallback callback){
        QueryAsyncTask asyncTask;
        Uri uri = Movie.CONTENT_URI;
        DataPair dataPair = new DataPair(uri,Movie.TAGS+" = ?",new String[]{Constants.TAGS_WISHLIST});
        synchronized (ConsumerMovieLocalData.class){
            asyncTask = new QueryAsyncTask(mContentResolver,callback);
            asyncTask.execute(dataPair);
        }

    }

    @Override
    public ArrayList<Movie> getWishListMoviesLocalSync(String tags) {
        Uri uri = Movie.CONTENT_URI;
        DataPair dataPair = new DataPair(uri,Movie.TAGS+" = ?",new String[]{Constants.TAGS_WISHLIST});
        return (ArrayList) Helper.mappingCursorToMovies(mContentResolver.query(uri,null,dataPair.selection,dataPair.argument,null));
    }

    @Override
    public void getMovieByIdLocal(int id, LoadDataCallback callback) {
        QueryByIdAsyncTask asyncTask;
        Uri uri = ContentUris.withAppendedId(Movie.CONTENT_URI,id);
        synchronized (ConsumerMovieLocalData.class){
            asyncTask = new QueryByIdAsyncTask(mContentResolver,callback);
            asyncTask.execute(uri);
        }

    }

    @Override
    public void updateMovieLocal(Movie movie, UpdateDataCallback callback) {
        UpdateAsyncTask asyncTask ;
        DataPair dataPair = new DataPair(Movie.CONTENT_URI,Helper.mappingMoviesToContent(movie));
        synchronized (ConsumerMovieLocalData.class){
            asyncTask = new UpdateAsyncTask(mContentResolver,callback);
        }
        asyncTask.execute(dataPair);
    }

    @Override
    public void deleteMovieLocal(Movie movie, IConsumerMovieDataSource.DBSource.UpdateDataCallback callback){

    }

    @Override
    public void deleteMovieByIdLocal(int id, UpdateDataCallback callback) {
        DeleteAsyncTask asyncTask;
        Uri uri = ContentUris.withAppendedId(Movie.CONTENT_URI,id);
        synchronized (ConsumerMovieLocalData.class){
            asyncTask = new DeleteAsyncTask(mContentResolver,callback);
            asyncTask.execute(uri);
        }
    }
}
