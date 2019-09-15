package id.shobrun.moviecatalogue.utils.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.utils.Notifications;
import id.shobrun.moviecatalogue.views.DetailMovieActivity;
import retrofit2.Response;

import static id.shobrun.moviecatalogue.utils.alarm.ReminderReceiver.BUNDLE_INTENT;
import static id.shobrun.moviecatalogue.utils.alarm.ReminderReceiver.DAILY_REMINDER;
import static id.shobrun.moviecatalogue.utils.alarm.ReminderReceiver.EXTRA_INTENT;
import static id.shobrun.moviecatalogue.utils.alarm.ReminderReceiver.EXTRA_MESSAGE;
import static id.shobrun.moviecatalogue.utils.alarm.ReminderReceiver.EXTRA_TITLE;
import static id.shobrun.moviecatalogue.utils.alarm.ReminderReceiver.EXTRA_TYPE;
import static id.shobrun.moviecatalogue.utils.alarm.ReminderReceiver.NOTIF_ID_DAILY;
import static id.shobrun.moviecatalogue.utils.alarm.ReminderReceiver.NOTIF_ID_RELEASE;


public class NotificationService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intents) {
        Context context = getApplicationContext();
        Intent intent = intents.getParcelableExtra(BUNDLE_INTENT);
        String type = intent.getStringExtra(EXTRA_TYPE);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        int idNotif = (type.equalsIgnoreCase(DAILY_REMINDER))?NOTIF_ID_DAILY:NOTIF_ID_RELEASE;
        if(type.equalsIgnoreCase(DAILY_REMINDER)){
            PendingIntent pIntent = intent.getParcelableExtra(EXTRA_INTENT);
            doDaily(context,title,message,idNotif,pIntent);
        }else{
            doReleaseReminder(context,idNotif,1);
        }
    }
    public void doDaily(Context context, String title, String message, int id, PendingIntent pendingIntent){
        Notifications.showNotification(context,title,message,id,pendingIntent);
    }
    public void doReleaseReminder(final Context context, final int id, final int counter){
        MovieRepository repository = MovieRepository.getInstance(context);
        repository.getReleaseMovie(new IMoviesDataSource.ApiSource.OnFinishedListener() {
            @Override
            public void onFinished(Response<MovieListResponse> response) {
                Movie movie = response.body().getResults().get(0);
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE,movie);

                PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                Notifications.showNotification(context,movie.getTitle(),movie.getOverview(),id,pendingIntent);
            }

            @Override
            public void onRefresh(ArrayList<Movie> movies) {

            }

            @Override
            public void onError(Response<MovieListResponse> response) {
                Log.d(getClass().getSimpleName(), "onError: "+"error");
                if(counter<10){
                    doReleaseReminder(context,id,counter+1);
                }

            }
            @Override
            public void onFailure(Throwable t) {
                Log.d(getClass().getSimpleName(), "onError: "+"failure");
                if(counter<10){
                    doReleaseReminder(context,id,counter+1);
                }
            }
        });
    }
}
