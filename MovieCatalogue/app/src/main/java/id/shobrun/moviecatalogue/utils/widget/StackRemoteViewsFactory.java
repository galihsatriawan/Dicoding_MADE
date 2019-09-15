package id.shobrun.moviecatalogue.utils.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.database.MovieCatalogueDatabase;
import id.shobrun.moviecatalogue.database.dao.MovieDao;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.DetailMovieActivity;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieRepository repository ;
    public StackRemoteViewsFactory(Context context,Intent intent){
        this.context = context;
        this.repository = new MovieRepository(context);
        int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        if(widgetId!= AppWidgetManager.INVALID_APPWIDGET_ID){

        }
    }
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDataSetChanged() {
        Log.d(TAG, "onDataSetChanged: ");
        final long identityToken = Binder.clearCallingIdentity();
        movies =repository.getLikeMoviesLocalSync(Constants.TAGS_FAVORITE);
        Binder.restoreCallingIdentity(identityToken);

    }
    @Override
    public void onDestroy() {
        movies.clear();
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.movie_widget_item);
        Log.d(TAG, "getViewAt: ");
            if (movies.size()>0){
                Movie movie = movies.get(position);
                try {
                    Bitmap bitmap = Glide.with(context)
                            .asBitmap()
                            .load(Constants.BACKDROP_BASE_URL+movie.getBackdrop_path())
                            .submit(200,100)
                            .get();
                    rv.setImageViewBitmap(R.id.image_poster_widget,bitmap);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                rv.setTextViewText(R.id.text_title_widget,movie.getTitle());

                // store the object in the extras so the main activity can use it

                Intent fillIntent = new Intent();
                fillIntent.putExtra(DetailMovieActivity.EXTRA_ID_MOVIE,movie.getId());
                rv.setOnClickFillInIntent(R.id.stack_view_item, fillIntent);

            }

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
