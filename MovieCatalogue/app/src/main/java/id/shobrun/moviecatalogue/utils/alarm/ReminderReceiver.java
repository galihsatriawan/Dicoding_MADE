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
import id.shobrun.moviecatalogue.utils.services.NotificationService;
import id.shobrun.moviecatalogue.views.DetailMovieActivity;
import retrofit2.Response;

public class ReminderReceiver extends BroadcastReceiver {
    private final String TAG = getClass().getSimpleName();
    public static final String DAILY_REMINDER = "DailyReminder";
    public static final String RELEASE_REMINDER = "ReleaseReminder";
    public static final String BUNDLE_INTENT = "intent";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_TITLE= "title";
    public static final String EXTRA_MESSAGE= "message";
    public static final String EXTRA_INTENT= "intent";

    public static final int ID_DAILY = 10;
    public static final int ID_RELEASE = 11;

    public static final int NOTIF_ID_DAILY = 100;
    public static final int NOTIF_ID_RELEASE = 101;
    public ReminderReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d(TAG, "onReceive: ");

        Intent service = new Intent(context, NotificationService.class);
        service.putExtra(BUNDLE_INTENT,intent);
        context.startService(service);

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

        Calendar cal_now = Calendar.getInstance();
        int idAlarm = (type.equalsIgnoreCase(DAILY_REMINDER))?ID_DAILY:ID_RELEASE;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,idAlarm,intent,0);

        if(alarmManager!=null){
            long timemillis = calendar.getTimeInMillis()
                    - cal_now.getTimeInMillis();
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,timemillis+System.currentTimeMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
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


}
