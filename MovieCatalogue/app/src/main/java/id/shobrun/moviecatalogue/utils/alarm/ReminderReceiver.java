package id.shobrun.moviecatalogue.utils.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import id.shobrun.moviecatalogue.repositories.MovieRepository;
import id.shobrun.moviecatalogue.utils.Notifications;
import id.shobrun.moviecatalogue.views.DetailMovieActivity;
import retrofit2.Response;

public class ReminderReceiver extends BroadcastReceiver {
    public static final String DAILY_REMINDER = "DailyReminder";
    public static final String RELEASE_REMINDER = "ReleaseReminder";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_TITLE= "title";
    public static final String EXTRA_MESSAGE= "message";
    public static final String EXTRA_INTENT= "intent";

    private final int ID_DAILY = 10;
    private final int ID_RELEASE = 11;

    private final int NOTIF_ID_DAILY = 100;
    private final int NOTIF_ID_RELEASE = 101;
    public ReminderReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra(EXTRA_TYPE);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        int idNotif = (type.equalsIgnoreCase(DAILY_REMINDER))?NOTIF_ID_DAILY:NOTIF_ID_RELEASE;
        PendingIntent pIntent = (PendingIntent)intent.getParcelableExtra(EXTRA_INTENT);
        if(type.equalsIgnoreCase(DAILY_REMINDER)){
            doDaily(context,title,message,idNotif,pIntent);
        }else{
            doReleaseReminder(context,idNotif,1);
        }
    }

    public void setRepeatAlarm(Context context, String type, String time,String title, String message,PendingIntent pIntent){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(EXTRA_TYPE,type);
        intent.putExtra(EXTRA_TITLE,title);
        intent.putExtra(EXTRA_MESSAGE,message);
        intent.putExtra(EXTRA_INTENT,pIntent);

        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND,0);

        int idAlarm = (type.equalsIgnoreCase(DAILY_REMINDER))?ID_DAILY:ID_RELEASE;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,idAlarm,intent,0);

        if(alarmManager!=null){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        }

    }

    public void setRepeatAlarm(Context context, String type, String time){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(EXTRA_TYPE,type);
        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND,0);

        int idAlarm = (type.equalsIgnoreCase(DAILY_REMINDER))?ID_DAILY:ID_RELEASE;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,idAlarm,intent,0);

        if(alarmManager!=null){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        }

    }
    public void cancelAlarm(Context context,String type){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,ReminderReceiver.class);
        int requestCode = (type.equalsIgnoreCase(DAILY_REMINDER))?ID_DAILY:ID_RELEASE;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,requestCode,intent,0);
        pendingIntent.cancel();

        if(alarmManager!=null){
            alarmManager.cancel(pendingIntent);
        }
    }

    public void doDaily(Context context, String title, String message,int id, PendingIntent pendingIntent){
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

                PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

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