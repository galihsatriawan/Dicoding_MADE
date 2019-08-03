package id.shobrun.myscheduler;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class GetCurrentWeatherJobService extends JobService {
    public static final String TAG  = GetCurrentWeatherJobService.class.getSimpleName();

    //Isikan dengan API Key dari openweathermap
    final String APP_ID ="35fe2d3ceee6b6164ead9eac50424a4f";

    final String CITY = "Malang";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: Executed");
        getCurrentWeather(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: Executed");
        return true;
    }

    private void getCurrentWeather(final JobParameters job){
        Log.d(TAG, "Running ");
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+CITY+"&appid="+APP_ID;
        Log.e(TAG, "getCurrentWeather: "+url );
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    String currentWeather = responseObject.getJSONArray("weather").getJSONObject(0).getString("main");
                    String description = responseObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    double tempKelvin = responseObject.getJSONObject("main").getDouble("temp");

                    double tempCelcius = tempKelvin - 273;
                    String temperature = new DecimalFormat("##.##").format(tempCelcius);

                    String title = "Current Weather";
                    String message = currentWeather + ", " +description+" with"+temperature+" celcius";
                    int notifId = 100;

                    // Show Notification
                    showNotification(getApplicationContext(),title,message,notifId);
                    jobFinished(job,false);
                }catch (Exception e){
                    jobFinished(job,true);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Ketika gagal maka job harus di reschedule
                jobFinished(job,true);
            }
        });
    }
    private void showNotification(Context context,String title, String description , int notifId){
        String CHANNEL_ID = "Channel 1";
        String CHANNEL_NAME ="Job Scheduler Channel";
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound  = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_replay_black_24dp)
                .setContentText(description)
                .setColor(ContextCompat.getColor(context,android.R.color.black))
                .setVibrate(new long[]{1000,1000,1000,1000})
                .setSound(alarmSound);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000,1000,1000,1000});
            if(notificationManagerCompat != null){
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if(notificationManagerCompat != null){
            notificationManagerCompat.notify(notifId,notification);
        }
    }
}
