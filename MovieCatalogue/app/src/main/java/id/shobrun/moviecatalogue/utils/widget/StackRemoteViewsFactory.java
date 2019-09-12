package id.shobrun.moviecatalogue.utils.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.DetailMovieActivity;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieRepository repository ;
    public StackRemoteViewsFactory(Context context,Intent intent){
        this.context = context;
        this.repository = new MovieRepository(context);
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();
            repository.getLikeMoviesLocal(Constants.TAGS_FAVORITE, new IMoviesDataSource.DBSource.LoadDataCallback() {
                @Override
                public void onPreLoad() {

                }

                @Override
                public <T> void onLoadSuccess(T res) {
                    ArrayList<Movie> movies = (ArrayList<Movie>) res;
                    StackRemoteViewsFactory.this.movies = movies;
                }
            });
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

        if(position <= getCount()){
            Movie movie = movies.get(position);
            try {
                Bitmap bitmap = Glide.with(context)
                        .asBitmap()
                        .load(movie.getBackdrop_path())
                        .submit(512,512)
                        .get();
                rv.setImageViewBitmap(R.id.image_poster_widget,bitmap);
            } catch (Throwable t) {
                t.printStackTrace();
            }
            rv.setTextViewText(R.id.text_title_widget,movie.getTitle());

            // store the object in the extras so the main activity can use it
            Bundle extras = new Bundle();
            extras.putParcelable(DetailMovieActivity.EXTRA_MOVIE,movie);
            Intent fillIntent = new Intent();
            fillIntent.putExtras(extras);
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
        return true;
    }
}
